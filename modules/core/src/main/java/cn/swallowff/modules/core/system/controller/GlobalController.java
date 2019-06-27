package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author swallowff
 * @description
 * @create 2019/6/24
 */
@Controller
@RequestMapping("/")
public class GlobalController extends BaseController {
    @RequestMapping("")
    public String nonPath(){
        return REDIRECT + "/a/index";
    }

    @RequestMapping("/a/index")
    public String index(){
        return "admin/pages2/index";
    }

//    @RequestMapping("error/404")
//    public String error404(){
//        return "error/404";
//    }
//
//    @RequestMapping("error/500")
//    public String error500(){
//        return "error/500";
//    }

}
