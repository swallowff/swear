package cn.swallowff.modules.core.modules.system.controller;

import cn.swallowff.common.command.CmdExecuter;
import cn.swallowff.common.idgen.IdGenerate;
import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.config.properties.CoreProperties;
import cn.swallowff.modules.core.config.properties.SwearEnvProperties;
import cn.swallowff.modules.core.modules.system.entity.MediaGallery;
import cn.swallowff.modules.core.modules.system.service.MediaGalleryService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author swallowff
 * @create 2019/07/28
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/sys/mediaGallery")
public class MediaGalleryController extends BaseController {

    @Autowired
    private MediaGalleryService mediaGalleryService;
    @Autowired
    private CoreProperties coreProperties;
    @Autowired
    private SwearEnvProperties swearEnvProperties;

    @RequestMapping(value = "list.html")
    @Permission(value = "sys-mediaGallery-list")
    public String listHtml(){
        return "/pages/admin/system/mediaGallery/mediaGallery-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/system/mediaGallery/mediaGallery-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        MediaGallery mediaGallery = mediaGalleryService.selectById(id);
        model.addAttribute("mediaGallery",mediaGallery);
        return "/pages/admin/system/mediaGallery/mediaGallery-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(MediaGallery mediaGallery){
        PageResp<MediaGallery> pageResp = mediaGalleryService.findPage(mediaGallery);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(MediaGallery mediaGallery){
        BaseResp baseResp = BaseResp.newSuccess();
        mediaGalleryService.save(mediaGallery);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(MediaGallery mediaGallery){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == mediaGallery || StringUtils.isBlank(mediaGallery.getId())){
            return baseResp.paramsError();
        }
        int r = mediaGalleryService.updateSelective(mediaGallery);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = mediaGalleryService.delete(id);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "batchDel")
    @ResponseBody
    public BaseResp batchDel(String[] ids){
        BaseResp baseResp = BaseResp.newSuccess();
        int count = 0;
        for (String id : ids){
            int r = mediaGalleryService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "uploadVideo")
    @ResponseBody
    public BaseResp uploadVideo(MultipartFile file, HttpServletRequest request) {
        BaseResp baseResp = BaseResp.newSuccess();

//        int serverPort = request.getServerPort();
        String baseUrl = "";
//        if (serverPort == 80){
            baseUrl = swearEnvProperties.getMediaServer() + "/video/";
//        }else {
//            baseUrl = swearEnvProperties.getMediaServer() + ":" + request.getServerPort() + request.getContextPath() + "/upload/media/video/";//url访问路径
//        }
        String realPath = coreProperties.getFileUploadPath() + "/media/video"; //文件存储位置

        String orgFileName = file.getOriginalFilename();
        String suffix = orgFileName.substring(orgFileName.lastIndexOf("."), orgFileName.length());
        String dateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
        String fileDir = realPath + File.separator + dateStr + File.separator;
        FileUtils.createDirectory(fileDir);
        String fileName = String.valueOf(IdGenerate.uuid());
        File targetFile = new File(fileDir + fileName + suffix);
        try {
            targetFile.createNewFile();
            file.transferTo(targetFile.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            return baseResp.putError("视频保存失败");
        }

        //转换成flv格式
//        String command = swearEnvProperties.getFfmpegBin() + " -i "+targetFile.getAbsolutePath()+" -vcodec copy " + targetFile.getParentFile().getAbsolutePath() + File.separator + fileName + ".flv";
//        CmdExecuter.exec(Arrays.asList(command.split(" ")), (line) -> {
//            System.out.println(line);
//        });

        //推流
//        String command = coreProperties.getFfmpegBin() + " -re -i " + targetFile.getAbsolutePath() + " -vcodec libx264 -acodec aac -f flv " + "rtmp://127.0.0.1/live/test";
//        CmdExecuter.exec(Arrays.asList(command.split(" ")), (line) -> {
//            System.out.println(line);
//        });

        String targetPath = baseUrl + dateStr + "/";
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("url", targetPath + fileName + suffix);
//        respMap.put("url", targetPath + fileName + ".flv");
        respMap.put("originName", orgFileName);
        respMap.put("localPath",targetFile.getAbsolutePath().replaceAll("\\\\","\\\\\\\\"));
        respMap.put("name", fileName + suffix);
        respMap.put("size", file.getSize());
        respMap.put("format", file.getContentType());
        respMap.put("mediaType",0);
        return baseResp.putSuccess(respMap);
    }

    @RequestMapping(value = "uploadAudio")
    @ResponseBody
    public BaseResp uploadAudio(MultipartFile file, HttpServletRequest request) {
        BaseResp baseResp = BaseResp.newSuccess();

        int serverPort = request.getServerPort();
        String baseUrl = "";
        if (serverPort == 80){
            baseUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/upload/media/audio/";
        }else {
            baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/audio/";//url访问路径
        }
        String realPath = coreProperties.getFileUploadPath() + "/media/audio"; //文件存储位置

        String orgFileName = file.getOriginalFilename();
        String suffix = orgFileName.substring(orgFileName.lastIndexOf("."), orgFileName.length());
        String dateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
        String fileDir = realPath + File.separator + dateStr + File.separator;
        FileUtils.createDirectory(fileDir);
        String fileName = String.valueOf(IdGenerate.uuid()) + suffix;
        File targetFile = new File(fileDir + fileName);
        try {
            targetFile.createNewFile();
            file.transferTo(targetFile.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            return baseResp.putError("音频保存失败");
        }
        String targetPath = baseUrl + dateStr + "/";
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("url", targetPath + fileName);
        respMap.put("originName", orgFileName);
        respMap.put("localPath",targetFile.getAbsolutePath().replaceAll("\\\\","\\\\\\\\"));
        respMap.put("name", fileName);
        respMap.put("size", file.getSize());
        respMap.put("format", file.getContentType());
        respMap.put("mediaType",1);
        return baseResp.putSuccess(respMap);
    }
}
