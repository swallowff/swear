package cn.swallowff.modules.core.shiro.service;

import cn.swallowff.modules.core.shiro.ShiroUser;
import cn.swallowff.modules.core.system.entity.User;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.List;

public interface UserAuthService {

    /**
     * 根据账户获取用户
     * @return
     */
    User user(String account);

    /**
     * 根据系统用户获取Shiro用户
     * @param user
     * @return
     */
    ShiroUser shiroUser(User user);

    /**
     * 获取角色的权限列表
     * @param roleId
     * @return
     */
    List<String> findPermissionsByRole(Integer roleId);

    /**
     * 获取Shiro认证信息
     * @param shiroUser
     * @param user
     * @param realName
     * @return
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realName);


}
