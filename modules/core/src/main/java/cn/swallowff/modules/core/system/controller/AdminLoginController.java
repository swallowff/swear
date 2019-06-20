package cn.swallowff.modules.core.system.controller;

import cn.swallowff.common.lang.StringUtils;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.validator.Validator;
import cn.swallowff.modules.core.excepiton.InvalidKaptchaException;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.util.KaptchaUtil;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Hash;
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
public class AdminLoginController extends BaseController {

    /**
     * 登录页面跳转
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request){
        return "admin/pages/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(@RequestParam(value = "account") String account,
                          @RequestParam(value = "password") String password, Boolean rememberme, String kaptcha, RedirectAttributes redirectAttributes, Model model){
        if (KaptchaUtil.getKaptchaOnOff()) {
            Validator.kaptcha(kaptcha);
        }
        Subject subject = ShiroKit.getSubject();
        subject.login(new UsernamePasswordToken(account.trim(),password.trim(),rememberme == null ? false : rememberme));
        return "admin/pages/index";
    }

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
        respMap.put("access_token",getSession().getId());
        return BaseResp.newSuccess().setData(respMap);
    }


}
