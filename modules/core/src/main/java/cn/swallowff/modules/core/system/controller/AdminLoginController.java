package cn.swallowff.modules.core.system.controller;

import cn.swallowff.common.lang.StringUtils;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.excepiton.InvalidKaptchaException;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.util.KaptchaUtil;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("${swear.path.admin}")
public class AdminLoginController extends BaseController {

    /**
     * 登录页面跳转
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request){
        return "admin/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "account") String account,
                          @RequestParam(value = "password") String password, Boolean rememberme, String kaptcha, RedirectAttributes redirectAttributes, Model model){
        if (KaptchaUtil.getKaptchaOnOff()) {
            kaptcha = kaptcha.trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
        }
        Subject subject = ShiroKit.getSubject();
        subject.login(new UsernamePasswordToken(account.trim(),password.trim(),rememberme == null ? false : rememberme));
        return "admin/index";
    }


}
