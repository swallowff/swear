package cn.swallowff.modules.core.aop;

import cn.swallowff.modules.core.constant.exceptionenum.BizExceptionEnum;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.excepiton.InvalidKaptchaException;
import cn.swallowff.modules.core.excepiton.ServiceException;
import cn.swallowff.modules.core.logmgr.LogManager;
import cn.swallowff.modules.core.logmgr.factory.LogTaskFactory;
import cn.swallowff.modules.core.util.HttpContext;
import org.apache.shiro.authc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResp bussiness(ServiceException e, HttpServletRequest request){
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(),e));
        request.setAttribute("tip",e.getMessage());
        log.error("业务异常:",e);
        return new BaseResp(e.getCode(),e.getErrorMessage());
    }

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "/admin/login";
    }

    /**
     * 账号被冻结异常
     */
    @ExceptionHandler(AccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String account(AccountException e, Model model, HttpServletRequest request) {
        String account = request.getParameter("account");
        if (e instanceof DisabledAccountException){
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "账号被冻结", HttpContext.getIp()));
            model.addAttribute("tips", "账号被冻结");
        }else if (e instanceof UnknownAccountException){
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "账户不存在", HttpContext.getIp()));
            model.addAttribute("tips", "账户不存在");
        }else{
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "账户未知", HttpContext.getIp()));
            model.addAttribute("tips", "账户未知");
        }
        return "/admin/login";
    }


    /**
     * 密码错误异常
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model) {
        String account = HttpContext.getRequest().getParameter("account");
        if (e instanceof ExpiredCredentialsException){
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "凭证过期", HttpContext.getIp()));
            model.addAttribute("tips", "凭证过期");
        }else if (e instanceof IncorrectCredentialsException){
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "密码错误", HttpContext.getIp()));
            model.addAttribute("tips", "密码错误");
        }
        return "/admin/login";
    }

    /**
     * 验证码错误异常
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidKaptchaException e, Model model) {
        String account = HttpContext.getRequest().getParameter("account");
        LogManager.me().executeLog(LogTaskFactory.loginLog(account, "验证码错误", HttpContext.getIp()));
        model.addAttribute("tips", "验证码错误");
        return "/admin/login";
    }

    /**
     * 无权访问该资源异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public BaseResp credentials(UndeclaredThrowableException e) {
        HttpContext.getRequest().setAttribute("tips", "权限异常");
        log.error("权限异常!", e);
        return new BaseResp(BizExceptionEnum.NO_PERMITION.getCode(), BizExceptionEnum.NO_PERMITION.getMsg());
    }
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResp notFount(RuntimeException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        HttpContext.getRequest().setAttribute("tips", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new BaseResp(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMsg());
    }

}
