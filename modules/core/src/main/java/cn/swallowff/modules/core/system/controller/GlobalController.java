package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.system.entity.Menu;
import cn.swallowff.modules.core.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author swallowff
 * @description
 * @create 2019/6/24
 */
@Controller
@RequestMapping("/")
public class GlobalController extends BaseController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("")
    public String nonPath(){
        return REDIRECT + "/a/index";
    }

    @RequestMapping("/a")
    public String aPath(){return REDIRECT + "/a/index";}

    @RequestMapping("/a/index2")
    public String index(){
        return "admin/pages/index";
    }

    @RequestMapping("/a/index")
    public String index2(Model model){
        List<Menu> list = menuService.findMenuTree();
        model.addAttribute("menuList",list);
        return "admin/pages/index";
    }


}
