package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.Menu;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.MenuService;
import cn.swallowff.modules.core.system.wrapper.UserAjaxListDictWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author swallowff
 * @description
 * @create 2019/6/29
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "list.html")
    public String listHtml(Menu menu){
        return "/admin/pages/system/menu/menu-list";
    }

    @RequestMapping(value = "menuList.ajax")
    @ResponseBody
    public Object menuListAjax(Menu menu){
        PageResp<Menu> pageResp = menuService.findPage(menu);
//        UserAjaxListDictWrapper wrapper = new UserAjaxListDictWrapper(pageResp.getDataList());
//        List<Map<String,Object>> wrapList = wrapper.wrapList();
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

}
