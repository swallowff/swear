package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.system.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author swallowff
 * @description
 * @create 2019/6/29
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/menu")
public class MenuController extends BaseController {

    @RequestMapping(value = "list.html")
    public String listHtml(Menu menu){

        return "";
    }

}
