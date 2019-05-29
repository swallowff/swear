package cn.swallowff.modules.core.cmomon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shenyu
 * @create 19-5-22
 */
@Controller
@RequestMapping(value = "error")
public class ErrorController {

    @RequestMapping("404")
    public String page404(){
        return "common/error/404";
    }

    @RequestMapping("500")
    public String page500(){
        return "common/error/500";
    }
}
