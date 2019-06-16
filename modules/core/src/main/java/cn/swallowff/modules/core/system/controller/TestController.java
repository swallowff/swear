package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shenyu
 * @create 19-5-31
 */
@Controller
@RequestMapping(value = "/a/test")
public class TestController {

    @RequestMapping(value = "index")
    public String index(){
        return "admin/pages/index";
    }

    @RequestMapping(value = "home/console")
    public String home(){
        return "admin/pages/home/console";
    }

    @RequestMapping(value = "login")
    public String login(){
        return "admin/pages/login";
    }

    @RequestMapping(value = "ajax")
    @ResponseBody
    public BaseResp ajax(){
        return BaseResp.newSuccess();
    }

}
