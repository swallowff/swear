package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 * @description
 * @create 2019/7/1
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/upload")
public class UploadController {

    @RequestMapping(value = "img")
    @ResponseBody
    public BaseResp uploadImg(MultipartFile file){
        BaseResp baseResp = BaseResp.newSuccess();

        return baseResp;
    }

}
