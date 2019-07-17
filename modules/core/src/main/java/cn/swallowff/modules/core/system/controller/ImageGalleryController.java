package cn.swallowff.modules.core.system.controller;

import cn.swallowff.common.idgen.IdGenerate;
import cn.swallowff.common.image.ImageUtils;
import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.config.properties.CoreProperties;
import cn.swallowff.modules.core.system.entity.ImageGallery;
import cn.swallowff.modules.core.system.service.ImageGalleryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenyu
 * @create 2019/6/30
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/sys/imageGallery")
public class ImageGalleryController extends BaseController {

    @Autowired
    private ImageGalleryService imageGalleryService;
    @Autowired
    private CoreProperties coreProperties;

    @RequestMapping(value = "list.html")
    @Permission(value = "sys-imageGallery-list")
    public String listHtml() {
        return "/admin/pages/system/imageGallery/imageGallery-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml() {
        return "/admin/pages/system/imageGallery/imageGallery-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model) {
        ImageGallery imageGallery = imageGalleryService.selectById(id);
        model.addAttribute("imageGallery", imageGallery);
        return "/admin/pages/system/imageGallery/imageGallery-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(ImageGallery imageGallery) {
        PageResp<ImageGallery> pageResp = imageGalleryService.findPage(imageGallery);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(), pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(ImageGallery imageGallery) {
        BaseResp baseResp = BaseResp.newSuccess();
        imageGalleryService.save(imageGallery);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(ImageGallery imageGallery) {
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == imageGallery || StringUtils.isBlank(imageGallery.getId())) {
            return baseResp.paramsError();
        }
        int r = imageGalleryService.updateSelective(imageGallery);
        if (r == 1) {
            return baseResp;
        } else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id) {
        BaseResp baseResp = BaseResp.newSuccess();
        int r = imageGalleryService.delete(id);
        if (r == 1) {
            return baseResp;
        } else return baseResp.putError();
    }

    @RequestMapping(value = "batchDel")
    @ResponseBody
    public BaseResp batchDel(String[] ids) {
        BaseResp baseResp = BaseResp.newSuccess();
        int count = 0;
        for (String id : ids) {
            int r = imageGalleryService.delete(id);
            if (r == 1) {
                count++;
            }
        }
        if (count == ids.length) {
            return baseResp;
        } else return baseResp.putError();
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public BaseResp uploadImg(MultipartFile file, HttpServletRequest request) {
        BaseResp baseResp = BaseResp.newSuccess();

        String imgBaseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/img/";//url访问路径
        String realPath = coreProperties.getFileUploadPath() + "img"; //文件存储位置

        String orgFileName = file.getOriginalFilename();
        String suffix = orgFileName.substring(orgFileName.lastIndexOf("."), orgFileName.length());
        String dateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
        String fileDir = realPath + File.separator + dateStr + File.separator;
        FileUtils.createDirectory(fileDir);
        String fileName = String.valueOf(IdGenerate.uuid()) + suffix;
        File imgFile = new File(fileDir + fileName);
        try {
            imgFile.createNewFile();
            file.transferTo(imgFile.getAbsoluteFile());
            ImageUtils.thumbnails(imgFile, 720, 720, UploadController.IMG_COMPRESS_FORMAT);
        } catch (IOException e) {
            e.printStackTrace();
            return baseResp.putError("文件保存失败");
        }
        String targetPath = imgBaseUrl + dateStr + "/";
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("src", targetPath + fileName);
        respMap.put("thumbnail", targetPath + fileName + "." + UploadController.IMG_COMPRESS_FORMAT);
        respMap.put("originName", orgFileName);
        respMap.put("imgName", fileName);
        respMap.put("size", file.getSize());
        respMap.put("imgFormat", file.getContentType());
        return baseResp.putSuccess(respMap);
    }
}
