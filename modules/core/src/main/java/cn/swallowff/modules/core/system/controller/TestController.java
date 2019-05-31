package cn.swallowff.modules.core.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shenyu
 * @create 19-5-31
 */
@Controller
@RequestMapping(value = "/a/test")
public class TestController {

    @RequestMapping(value = "index")
    public String index(){
        return "admin/pages/index2";
    }

    @RequestMapping(value = "home/console")
    public String home(){
        return "admin/pages/home/console";
    }

}
