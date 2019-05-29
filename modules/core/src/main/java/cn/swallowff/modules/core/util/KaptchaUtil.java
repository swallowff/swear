package cn.swallowff.modules.core.util;

import cn.swallowff.modules.core.config.properties.CoreProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(CoreProperties.class).getKaptchaOpen();
    }
}
