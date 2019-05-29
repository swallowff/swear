package cn.swallowff.modules.core.shiro;

import cn.swallowff.modules.core.shiro.service.UserAuthService;
import cn.swallowff.modules.core.shiro.service.impl.UserAuthServiceImpl;
import cn.swallowff.modules.core.system.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
        return null;
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
