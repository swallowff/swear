package cn.swallowff.modules.core.shiro.service;

public interface UserRoleService {

    /**
     * 根据角色ID获取角色名称
     * @param roleId
     * @return
     */
    public String getRoleName(String roleId);


}
