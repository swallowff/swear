package cn.swallowff.admin.components.shiro.service.impl;

import cn.swallowff.admin.components.shiro.service.UserRoleService;
import cn.swallowff.admin.modules.system.entity.Role;
import cn.swallowff.admin.modules.system.entity.UserRoleRelation;
import cn.swallowff.admin.modules.system.service.RoleService;
import cn.swallowff.admin.modules.system.service.UserRoleRelationService;
import cn.swallowff.admin.util.SpringContextHolder;
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
