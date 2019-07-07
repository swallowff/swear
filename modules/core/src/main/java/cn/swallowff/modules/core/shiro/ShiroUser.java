package cn.swallowff.modules.core.shiro;

import cn.swallowff.modules.core.shiro.service.impl.UserRoleServiceImpl;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.entity.UserRoleRelation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author yushen
 * @date 2019年3月9日
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;          // 主键ID
    public String account;      // 账号
    public String name;         // 姓名
    public String deptId;      // 部门id
    public List<String> roleList; // 角色集
    public String deptName;        // 部门名称
    public List<String> roleNames; // 角色名称集


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public static ShiroUser fromSysUser(User user){
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.id = user.getId();
        shiroUser.account = user.getAccount();
        shiroUser.name = user.getName();
        shiroUser.deptId = user.getDeptId();
        List<UserRoleRelation> roleRelationList = UserRoleServiceImpl.me().findRoleListByUserId(user.getId());
        shiroUser.roleList = roleRelationList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        shiroUser.roleNames = roleRelationList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        return shiroUser;
    }
}
