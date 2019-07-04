package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.Menu;
import cn.swallowff.modules.core.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String listHtml(Menu menu,Model model){
        return "admin/pages/system/menu/menu-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(Menu menu,Model model){
        return "admin/pages/system/menu/menu-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        Menu menu = menuService.selectById(id);
        model.addAttribute("menu",menu);
        return "admin/pages/system/menu/menu-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(Menu menu){
        menu.setOrderBy("sort ASC");
        PageResp<Menu> pageResp = menuService.findPage(menu);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(Menu menu){
        BaseResp baseResp = BaseResp.newSuccess();
        Menu existMenu = menuService.selectByCode(menu.getCode());
        if (null != existMenu){
            return baseResp.putError("菜单CODE重复了哦");
        }
        menuService.save(menu);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(Menu menu){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == menu || StringUtils.isBlank(menu.getId())){
            return baseResp.paramsError();
        }
        if (null == menu.getIsMenu()){
            menu.setIsMenu(false);
        }
        int r = menuService.updateSelective(menu);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = menuService.delete(id);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "batchDel")
    @ResponseBody
    public BaseResp batchDel(String[] ids){
        BaseResp baseResp = BaseResp.newSuccess();
        int count = 0;
        for (String id : ids){
            int r = menuService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }



}
