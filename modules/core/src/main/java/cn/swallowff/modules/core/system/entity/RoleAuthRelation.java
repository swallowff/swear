package cn.swallowff.modules.core.system.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;

/**
 * @author shenyu
 * @create 2019/7/7
 */
public class RoleAuthRelation extends BaseEntity {
    private String roleId;
    private String authCode;
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
