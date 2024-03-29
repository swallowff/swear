package cn.swallowff.admin.cmomon.controller;

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
    public String page404() {
        return "pages/error/404";
    }

    @RequestMapping("500")
    public String page500() {
        return "pages/error/500";
    }
}
