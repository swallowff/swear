package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.excepiton.BizException;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.entity.UserRoleRelation;
import cn.swallowff.modules.core.system.service.UserRoleRelationService;
import cn.swallowff.modules.core.system.service.UserService;
import cn.swallowff.modules.core.system.wrapper.UserAjaxListDictWrapper;
import cn.swallowff.modules.core.system.wrapper.UserDateFormatWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author shenyu
 * @create 19-6-25
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @RequestMapping(value = "list.html")
    public String listHtml() {
        return "admin/pages/system/user/user-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml() {
        return "admin/pages/system/user/user-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model) {
        User user = userService.selectById(id);
        UserDateFormatWrapper wrapper = new UserDateFormatWrapper(user);
        model.addAttribute("user", wrapper.wrap());
        return "admin/pages/system/user/user-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(User user) {
        PageResp<User> pageResp = userService.findPage(user);
        UserAjaxListDictWrapper wrapper = new UserAjaxListDictWrapper(pageResp.getDataList());
        List<Map<String, Object>> wrapList = wrapper.wrapList();
        LayPageResp layPageResp = new LayPageResp(wrapList, pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(User user) {
        BaseResp baseResp = BaseResp.newSuccess();
        User existUser = userService.selectByAccount(user.getAccount());
        if (null != existUser) {
            throw new BizException(ResponseState.USER_ACCOUNT_EXISTS);
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            String salt = ShiroKit.getRandomSalt(8);
            user.setSalt(salt);
            user.setPassword(ShiroKit.md5(user.getPassword(), salt));
        }
        userService.save(user);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(User user) {
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == user || StringUtils.isBlank(user.getId())) {
            return baseResp.paramsError();
        }
        if (StringUtils.isNotBlank(user.getPassword()) && user.getPassword().length() <= 16) {
            String salt = ShiroKit.getRandomSalt(8);
            user.setSalt(salt);
            user.setPassword(ShiroKit.md5(user.getPassword(), salt));
        }
        int r = userService.updateSelective(user);
        if (r == 1) {
            return baseResp;
        } else return baseResp.putError();
    }


    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id) {
        BaseResp baseResp = BaseResp.newSuccess();
        if ("0".equals(id)) {
            return baseResp.putError("无法删除超级管理员");
        }
        int r = userService.delete(id);
        if (r == 1) {
            return baseResp;
        } else return baseResp.putError();
    }

    @RequestMapping(value = "batchSetupRoles")
    @ResponseBody
    public synchronized BaseResp batchSetupRoles(String userId, String[] roleIds) {
        BaseResp baseResp = BaseResp.newSuccess();
        if (StringUtils.isBlank(userId)) {
            return baseResp.putError("请选择用户");
        }
        if (ArrayUtils.isEmpty(roleIds)) {
            return baseResp.putError("请选择角色");
        }
        int count = 0;
        userRoleRelationService.delByUserId(userId);
        for (String roleId : roleIds) {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(userId);
            userRoleRelation.setRoleId(roleId);
            userRoleRelationService.save(userRoleRelation);
            count++;
        }

        if (count > 0) {
            return baseResp;
        } else return baseResp.putError();

    }

    @RequestMapping(value = "batchDel")
    @ResponseBody
    public BaseResp batchDel(String[] ids) {
        BaseResp baseResp = BaseResp.newSuccess();
        int count = 0;
        for (String id : ids) {
            if ("0".equals(id)) {
                continue;
            }
            int r = userService.delete(id);
            if (r == 1) {
                count++;
            }
        }
        if (count == ids.length) {
            return baseResp;
        } else return baseResp.putError();
    }


}
