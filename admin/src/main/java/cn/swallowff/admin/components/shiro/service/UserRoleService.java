package cn.swallowff.admin.components.shiro.service;

import cn.swallowff.admin.modules.system.entity.UserRoleRelation;

import java.util.List;

public interface UserRoleService {

    /**
     * 根据角色ID获取角色名称
     *
     * @param roleId
     * @return
     */
    String getRoleName(String roleId);

    List<UserRoleRelation> findRoleListByUserId(String userId);


}
