package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.Role;
import cn.swallowff.modules.core.system.service.RoleService;
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
@RequestMapping(value = "${swear.path.admin}/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "list.html")
    public String listHtml(Role role,Model model){
        return "admin/pages/system/role/role-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(Role role,Model model){
        return "admin/pages/system/role/role-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        Role role = roleService.selectById(id);
        model.addAttribute("role",role);
        return "admin/pages/system/role/role-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(Role role){
        role.setOrderBy("sort ASC");
        PageResp<Role> pageResp = roleService.findPage(role);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "roleTree.ajax")
    @ResponseBody
    public Object ajaxRoleTree(Role role){
        role.setOrderBy("sort ASC");
//        List<DeptTreeDto> list = roleService.findList(new Role());
//        BaseResp baseResp = BaseResp.newSuccess().setData(list);
        return null;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(Role role){
        BaseResp baseResp = BaseResp.newSuccess();
        roleService.save(role);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(Role role){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == role || StringUtils.isBlank(role.getId())){
            return baseResp.paramsError();
        }
        int r = roleService.update(role);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = roleService.delete(id);
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
            int r = roleService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }



}
