package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.entity.TreeEntity;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.dto.DtreeNode;
import cn.swallowff.modules.core.system.entity.Menu;
import cn.swallowff.modules.core.system.entity.Role;
import cn.swallowff.modules.core.system.entity.RoleAuthRelation;
import cn.swallowff.modules.core.system.service.MenuService;
import cn.swallowff.modules.core.system.service.RoleAuthRelationService;
import cn.swallowff.modules.core.system.service.RoleService;
import cn.swallowff.modules.core.system.wrapper.RoleDtreeNodeWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @Autowired
    private RoleAuthRelationService roleAuthRelationService;
    @Autowired
    private MenuService menuService;

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

    @RequestMapping(value = "dtree.ajax")
    @ResponseBody
    public Object dtree(Role role){
        BaseResp baseResp = BaseResp.newSuccess();
        role.setPids(TreeEntity.ROOT_ID);
        List<Role> list = roleService.findList(role);
        RoleDtreeNodeWrapper wrapper = new RoleDtreeNodeWrapper(list);
        return baseResp.setData(wrapper.wrapList());
    }

    @RequestMapping(value = "userDtree.ajax")
    @ResponseBody
    public Object userDtree(String userId){
        BaseResp baseResp = BaseResp.newSuccess();
        Role role = new Role();
        role.setPids(TreeEntity.ROOT_ID);
        List<DtreeNode> list = roleService.findDtreeNodeListWithUid(role,userId);
//        RoleDtreeNodeWrapper wrapper = new RoleDtreeNodeWrapper(list);
        return baseResp.setData(list);
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
        if ("1".equals(id)){
            return baseResp.putError("无法删除超级管理员角色");
        }
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
            if ("1".equals(id)){
                continue;
            }
            int r = roleService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "batchSetupAuth")
    @ResponseBody
    public BaseResp batchSetupAuth(String roleId,String[] menuIds){
        BaseResp baseResp = BaseResp.newSuccess();
        if (StringUtils.isBlank(roleId)){
            return baseResp.putError("请选择角色");
        }
        if (ArrayUtils.isEmpty(menuIds)){
            return baseResp.putError("请选择菜单");
        }
        //删除角色原来的权限
        roleAuthRelationService.delByRoleId(roleId);

        int count = 0;
        for (int j = 0; j < menuIds.length; j++){
            //添加角色菜单数据
            String menuId = menuIds[j];
            RoleAuthRelation roleAuthRelation = roleAuthRelationService.selectByRoleIdAndMenuId(roleId,menuId);
            if (null == roleAuthRelation){
                roleAuthRelation = new RoleAuthRelation();
            }
            roleAuthRelation.setRoleId(roleId);
            roleAuthRelation.setMenuId(menuId);
            Menu menu = menuService.selectById(menuId);
            if (null != menu){
                roleAuthRelation.setAuthCode(menu.getCode());
            }
            roleAuthRelationService.save(roleAuthRelation);
            count ++;
        }

        if (count > 0){
            return baseResp;
        }else return baseResp.putError();
    }

}
