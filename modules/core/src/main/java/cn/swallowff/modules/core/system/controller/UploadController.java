package cn.swallowff.modules.core.system.controller;

import cn.swallowff.common.idgen.IdGenerate;
import cn.swallowff.common.image.ImageUtils;
import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.common.lang.StringUtils;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.config.properties.CoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
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

    @Autowired
    private CoreProperties coreProperties;

    @RequestMapping(value = "img")
    @ResponseBody
    public BaseResp uploadImg(MultipartFile file, HttpServletRequest request){
        BaseResp baseResp = BaseResp.newSuccess();

        String imgBaseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/img/";//url访问路径
        String realPath = coreProperties.getFileUploadPath() + "img"; //文件存储位置

        String orgFileName = file.getOriginalFilename();
        String suffix = orgFileName.substring(orgFileName.lastIndexOf("."),orgFileName.length());
        String dateStr = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        String fileDir = realPath + File.separator + dateStr + File.separator;
        FileUtils.createDirectory(fileDir);
        String fileName = String.valueOf(IdGenerate.uuid()) + "." + suffix;
        File imgFile = new File(fileDir + fileName);
//        while (imgFile.exists()){
//            fileName = String.valueOf(System.currentTimeMillis()).concat(StringUtils.getRandomStr(8));
//            imgFile = new File( fileDir + File.separator + fileName);
//        }
        try{
            imgFile.createNewFile();
            file.transferTo(imgFile);
            ImageUtils.thumbnails(imgFile,720,720,"png");
        }catch (IOException e){
            e.printStackTrace();
            return baseResp.putError("文件保存失败");
        }
        Map<String,Object> respMap = new HashMap<>();
        respMap.put("url",imgBaseUrl  + dateStr + "/" + fileName);
        return baseResp.putSuccess(respMap);
    }

}
