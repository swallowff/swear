package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.validator.Validator;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.util.KaptchaUtil;
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

@Controller
@RequestMapping("${swear.path.admin}/login")
public class LoginController extends BaseController {

    /**
     * 登录页面跳转
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request){
        Subject subject = ShiroKit.getSubject();
        if (subject.isAuthenticated()){
            return REDIRECT + "/a/common/index";
        }else {
            return "admin/pages2/login";
        }
    }

    /**
     * form登录请求
     * @param account
     * @param password
     * @param rememberme
     * @param kaptcha
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "account") String account,
                          @RequestParam(value = "password") String password, Boolean rememberme, String kaptcha, RedirectAttributes redirectAttributes, Model model){
        if (KaptchaUtil.getKaptchaOnOff()) {
            Validator.kaptcha(kaptcha);
        }
        Subject subject = ShiroKit.getSubject();
        subject.login(new UsernamePasswordToken(account.trim(),password.trim(),rememberme == null ? false : rememberme));
        return REDIRECT + "/a/common/index";
    }

    /**
     * ajax登录请求
     * @param account
     * @param password
     * @param kaptcha
     * @return
     */
    @RequestMapping(value = "ajaxLogin",method = RequestMethod.POST)
    @ResponseBody
    public BaseResp ajaxLogin(@RequestParam(value = "account") String account,
                              @RequestParam(value = "password") String password, String kaptcha){
        if (KaptchaUtil.getKaptchaOnOff()) {
            Validator.kaptcha(kaptcha);
        }
        Subject subject = ShiroKit.getSubject();
        subject.login(new UsernamePasswordToken(account.trim(),password.trim()));
        HashMap<String,Object> respMap = new HashMap<>();
        respMap.put("accessToken",getSession().getId());
        return BaseResp.newSuccess().setData(respMap);
    }

    @RequestMapping(value = "logout")
    public String logout(){
        Subject subject = ShiroKit.getSubject();
        subject.logout();
        return REDIRECT + "/a/login/login";
    }


}
