package cn.swallowff.admin.modules.system.controller;

import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.components.shiro.ShiroKit;
import cn.swallowff.admin.components.shiro.ShiroUser;
import cn.swallowff.admin.modules.system.entity.Menu;
import cn.swallowff.admin.modules.system.entity.User;
import cn.swallowff.admin.modules.system.service.MenuService;
import cn.swallowff.admin.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String nonPath() {
        return REDIRECT + "/a/index";
    }

    @RequestMapping("/a")
    public String aPath() {
        return REDIRECT + "/a/index";
    }

    @RequestMapping("/a/home")
    public String home() {
        return "/pages/admin/homepage";
    }

    @RequestMapping("/a/index")
    public String index(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        if (null != shiroUser) {
            User user = userService.selectById(shiroUser.getId());
            if (null != user) {
                model.addAttribute("user", user);
            }
        }
        Menu menu = new Menu();
        menu.setId(Menu.ROOT_ID);
        menu.setOrderBy("sort ASC");
        menu.setUserId(shiroUser.id);
        List<Menu> list = menuService.findTree(menu).getChildren();
        model.addAttribute("menuList", list);
        return "pages/admin/index";
    }


}
