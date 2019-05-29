package cn.swallowff.modules.core.shiro.service.impl;

import cn.swallowff.modules.core.shiro.service.UserRoleService;
import cn.swallowff.modules.core.system.entity.Role;
import cn.swallowff.modules.core.system.service.RoleService;
import cn.swallowff.modules.core.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("springContextHolder")
public class UserRoleServiceImpl implements UserRoleService {
//    @Autowired
    private RoleService roleService;

    @Override
    public String getRoleName(String roleId) {
        Role role = roleService.selectById(roleId);
        return role.getName();
    }

    public static UserRoleService me(){
        return SpringContextHolder.getBean(UserRoleService.class);
    }


}
