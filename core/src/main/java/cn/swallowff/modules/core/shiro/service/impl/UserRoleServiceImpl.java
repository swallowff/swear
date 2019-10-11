package cn.swallowff.modules.core.shiro.service.impl;

import cn.swallowff.modules.core.shiro.service.UserRoleService;
import cn.swallowff.modules.core.modules.system.entity.Role;
import cn.swallowff.modules.core.modules.system.entity.UserRoleRelation;
import cn.swallowff.modules.core.modules.system.service.RoleService;
import cn.swallowff.modules.core.modules.system.service.UserRoleRelationService;
import cn.swallowff.modules.core.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DependsOn("springContextHolder")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Override
    public String getRoleName(String roleId) {
        Role role = roleService.selectById(roleId);
        return role.getName();
    }

    @Override
    public List<UserRoleRelation> findRoleListByUserId(String userId) {
        return userRoleRelationService.findByUserId(userId);
    }

    public static UserRoleService me() {
        return SpringContextHolder.getBean(UserRoleService.class);
    }


}
