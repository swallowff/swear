package cn.swallowff.modules.core.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @description
 * @create 2019/6/24
 */
@Controller
@RequestMapping("${swear.path.admin}/common")
public class CommonController {

    @RequestMapping("index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        return "admin/pages/index";
    }

    @RequestMapping("welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response){
        return "admin/pages/welcome";
    }

}
