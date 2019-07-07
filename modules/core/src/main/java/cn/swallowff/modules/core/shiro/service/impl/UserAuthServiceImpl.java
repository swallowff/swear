package cn.swallowff.modules.core.shiro.service.impl;

import cn.swallowff.modules.core.shiro.ShiroUser;
import cn.swallowff.modules.core.shiro.service.UserAuthService;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.system.service.RoleAuthRelationService;
import cn.swallowff.modules.core.system.service.UserService;
import cn.swallowff.modules.core.util.SpringContextHolder;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DependsOn({"springContextHolder","userService"})
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleAuthRelationService roleAuthRelationService;

    @Override
    public User user(String account) {
        return userService.selectByAccount(account);
    }

    //TODO
    @Override
    public ShiroUser shiroUser(User user) {
        return null;
    }

    @Override
    public List<String> findPermissionsByRole(String roleId) {
        return roleAuthRelationService.findPermissionsByRole(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realName) {
        String credentials = user.getPassword();
        //密码加盐处理
        String salt = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);
        return new SimpleAuthenticationInfo(shiroUser,credentials,credentialsSalt,realName);
    }

    public static UserAuthService me(){
        return SpringContextHolder.getBean(UserAuthServiceImpl.class);
    }
}
