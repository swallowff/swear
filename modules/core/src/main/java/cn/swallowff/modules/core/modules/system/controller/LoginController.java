package cn.swallowff.modules.core.modules.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.validator.Validator;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.excepiton.InvalidKaptchaException;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.util.KaptchaUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("${swear.path.admin}/login")
public class LoginController extends BaseController {

    /**
     * 登录页面跳转
     *
     * @return
     */
    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String loginHtml(HttpServletRequest request) {
        Subject subject = ShiroKit.getSubject();
        if (subject.isAuthenticated()) {
            return REDIRECT + "/a/index";
        } else {
            return "/pages/admin/login";
        }
    }

    /**
     * form登录请求
     *
     * @param account
     * @param password
     * @param rememberme
     * @param kaptcha
     * @return
     */
    @RequestMapping(value = "login.form", method = RequestMethod.POST)
    public String formLogin(@RequestParam(value = "account") String account,
                            @RequestParam(value = "password") String password, Boolean rememberme, String kaptcha, RedirectAttributes redirectAttributes, Model model) {
        if (KaptchaUtil.getKaptchaOnOff()) {
            Validator.kaptcha(kaptcha);
        }
        Subject subject = ShiroKit.getSubject();
        subject.login(new UsernamePasswordToken(account.trim(), password.trim(), rememberme == null ? false : rememberme));
        return REDIRECT + "/a/index";
    }

    /**
     * ajax登录请求
     *
     * @param account
     * @param password
     * @param kaptcha
     * @return
     */
    @RequestMapping(value = "login.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp ajaxLogin(@RequestParam(value = "account") String account,
                              @RequestParam(value = "password") String password, String kaptcha, boolean rememberme) {
        try {
            if (KaptchaUtil.getKaptchaOnOff()) {
                Validator.kaptcha(kaptcha);
            }
            Subject subject = ShiroKit.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account.trim(), password.trim(), rememberme);
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
//            logger.error("login error",e);
            return new BaseResp(ResponseState.INCORRECT_PASSWORD);
        } catch (InvalidKaptchaException e2) {
//            logger.error("kaptcha error",e2);
            return new BaseResp(ResponseState.INVALID_KAPTCHA);
        }
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("accessToken", getSession().getId());
        return BaseResp.newSuccess().setData(respMap);
    }

    @RequestMapping(value = "logout")
    @ResponseBody
    public BaseResp logout() {
        Subject subject = ShiroKit.getSubject();
        subject.logout();
        return BaseResp.newSuccess();
    }


}
