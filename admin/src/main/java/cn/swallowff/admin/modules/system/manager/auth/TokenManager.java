package cn.swallowff.admin.modules.system.manager.auth;

/**
 * @author shenyu
 * @create 2019/8/14
 */
public interface TokenManager {

    String generateToken();

    void refreshToken();

    void removeToken();


}
