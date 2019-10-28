package cn.swallowff.admin.modules.system.controller;

import cn.swallowff.common.idgen.IdGenerate;
import cn.swallowff.common.image.ImageUtils;
import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.config.properties.CoreProperties;
import cn.swallowff.admin.config.properties.SwearEnvProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @description
 * @create 2019/7/1
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/upload")
public class UploadController {
    public static final String IMG_COMPRESS_FORMAT = "png";

    @Autowired
    private CoreProperties coreProperties;
    @Autowired
    private SwearEnvProperties swearEnvProperties;

    @RequestMapping(value = "img")
    @ResponseBody
    public BaseResp uploadImg(MultipartFile file, HttpServletRequest request) {
        BaseResp baseResp = BaseResp.newSuccess();

        int serverPort = request.getServerPort();
        String imgBaseUrl = "";
        if (serverPort == 80){
            imgBaseUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/upload/img/";
        }else {
            imgBaseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/img/";//url访问路径
        }
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
            ImageUtils.thumbnails(imgFile, 720, 720, IMG_COMPRESS_FORMAT);
        } catch (IOException e) {
            e.printStackTrace();
            return baseResp.putError("文件保存失败");
        }
        String targetPath = imgBaseUrl + dateStr + "/";
        Map<String, Object> respMap = new HashMap<>(3);
        respMap.put("src", targetPath + fileName);
        respMap.put("thumbnail", targetPath + fileName + "." + IMG_COMPRESS_FORMAT);
        return baseResp.putSuccess(respMap);
    }

    @RequestMapping(value = "video")
    @ResponseBody
    public BaseResp uploadVideo(MultipartFile video, HttpServletRequest request) {
        BaseResp baseResp = BaseResp.newSuccess();

        int serverPort = request.getServerPort();
        String baseUrl = "";
//        if (serverPort == 80){
//            baseUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/upload/video/";
//        }else {
//            baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/video/";//url访问路径
//        }
        baseUrl = swearEnvProperties.getServerUrl() + request.getContextPath() + "/upload/img/";
        String realPath = coreProperties.getFileUploadPath() + "video"; //文件存储位置

        String orgFileName = video.getOriginalFilename();
        String suffix = orgFileName.substring(orgFileName.lastIndexOf("."), orgFileName.length());
        String dateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
        String fileDir = realPath + File.separator + dateStr + File.separator;
        FileUtils.createDirectory(fileDir);
        String fileName = String.valueOf(IdGenerate.uuid()) + suffix;
        File imgFile = new File(fileDir + fileName);
        try {
            imgFile.createNewFile();
            video.transferTo(imgFile.getAbsoluteFile());
//            ImageUtils.thumbnails(imgFile, 720, 720, IMG_COMPRESS_FORMAT);
        } catch (IOException e) {
            e.printStackTrace();
            return baseResp.putError("文件保存失败");
        }
        String targetPath = baseUrl + dateStr + "/";
        Map<String, Object> respMap = new HashMap<>(3);
        respMap.put("url", targetPath + fileName);
        return baseResp.putSuccess(respMap);
    }

}
