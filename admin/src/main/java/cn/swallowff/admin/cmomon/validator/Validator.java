package cn.swallowff.admin.cmomon.validator;

import cn.swallowff.common.lang.StringUtils;
import cn.swallowff.admin.cmomon.excepiton.InvalidKaptchaException;
import cn.swallowff.admin.util.HttpContext;
import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author shenyu
 * @create 19-6-21
 */
public class Validator {

    public static boolean kaptcha(String kaptcha) throws InvalidKaptchaException {
        HttpSession httpSession = ((HttpServletRequest) Objects.requireNonNull(HttpContext.getRequest())).getSession();
        ;
        kaptcha = kaptcha.trim();
        String code = (String) httpSession.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
            throw new InvalidKaptchaException();
        }
        return true;
    }

}
