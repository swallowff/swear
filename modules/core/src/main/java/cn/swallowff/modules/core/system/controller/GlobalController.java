package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.shiro.ShiroUser;
import cn.swallowff.modules.core.system.entity.Menu;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.MenuService;
import cn.swallowff.modules.core.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.Subject;
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
        return "admin/pages/homepage";
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
        return "admin/pages/index";
    }


}
