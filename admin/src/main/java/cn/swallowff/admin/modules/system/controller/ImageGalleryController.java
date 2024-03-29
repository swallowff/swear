package cn.swallowff.admin.modules.system.controller;

import cn.swallowff.common.idgen.IdGenerate;
import cn.swallowff.common.image.ImageUtils;
import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.admin.cmomon.annotion.Permission;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.cmomon.resp.LayPageResp;
import cn.swallowff.admin.cmomon.resp.PageResp;
import cn.swallowff.admin.config.properties.CoreProperties;
import cn.swallowff.admin.config.properties.SwearEnvProperties;
import cn.swallowff.admin.modules.system.entity.ImageGallery;
import cn.swallowff.admin.modules.system.service.ImageGalleryService;
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
    @Autowired
    private SwearEnvProperties swearEnvProperties;

    @RequestMapping(value = "list.html")
    @Permission(value = "sys-imageGallery-list")
    public String listHtml() {
        return "/pages/admin/system/imageGallery/imageGallery-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml() {
        return "/pages/admin/system/imageGallery/imageGallery-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model) {
        ImageGallery imageGallery = imageGalleryService.selectById(id);
        model.addAttribute("imageGallery", imageGallery);
        return "/pages/admin/system/imageGallery/imageGallery-edit";
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
        int serverPort = request.getServerPort();
        String imgBaseUrl = "";
//        if (serverPort == 80){
//            imgBaseUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/upload/img/";
//        }else if (serverPort == 443){
//            imgBaseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/img/";//url访问路径
//        }
        imgBaseUrl = swearEnvProperties.getServerUrl() + request.getContextPath() + "/upload/img/";
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
