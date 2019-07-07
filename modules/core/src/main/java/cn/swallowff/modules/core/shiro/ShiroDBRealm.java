package cn.swallowff.modules.core.shiro;

import cn.swallowff.common.lang.StringUtils;
import cn.swallowff.modules.core.shiro.service.UserAuthService;
import cn.swallowff.modules.core.shiro.service.impl.UserAuthServiceImpl;
import cn.swallowff.modules.core.system.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroDBRealm extends AuthorizingRealm {


    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserAuthService userAuthService = UserAuthServiceImpl.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userAuthService.user(token.getUsername());
        if (null == user){
            throw new UnknownAccountException("用户名不存在");
        }
        ShiroUser shiroUser = ShiroUser.fromSysUser(user);
        return userAuthService.info(shiroUser,user,super.getName());
    }

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserAuthService userAuthService = UserAuthServiceImpl.me();
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        List<String> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        for (String roleId : roleList) {
            List<String> permissions = userAuthService.findPermissionsByRole(roleId);
            if (permissions != null) {
                for (String permission : permissions) {
                    if (StringUtils.isNotBlank(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
//            String roleName = userAuthService.findRoleNameByRoleId(roleId);
//            roleNameSet.add(roleName);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
