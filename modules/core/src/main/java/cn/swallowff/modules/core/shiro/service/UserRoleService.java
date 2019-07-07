package cn.swallowff.modules.core.shiro.service;

import cn.swallowff.modules.core.system.entity.UserRoleRelation;

import java.util.List;

public interface UserRoleService {

    /**
     * 根据角色ID获取角色名称
     * @param roleId
     * @return
     */
    String getRoleName(String roleId);

    List<UserRoleRelation> findRoleListByUserId(String userId);


}
