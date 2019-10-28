package cn.swallowff.admin.util;

import cn.swallowff.admin.config.properties.CoreProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(CoreProperties.class).isKaptchaOpen();
    }
}
