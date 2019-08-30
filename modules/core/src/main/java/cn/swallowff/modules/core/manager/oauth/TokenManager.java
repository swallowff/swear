package cn.swallowff.modules.core.manager.oauth;

/**
 * @author shenyu
 * @create 2019/8/14
 */
public interface TokenManager {

    String generateToken();

    void refreshToken();

    void removeToken();


}
