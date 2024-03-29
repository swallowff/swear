package cn.swallowff.admin.cmomon.aop;

import cn.swallowff.admin.components.shiro.ShiroKit;
import cn.swallowff.admin.components.shiro.ShiroUser;
import cn.swallowff.admin.util.HttpContext;
import cn.swallowff.admin.cmomon.constant.exceptionenum.BizExceptionEnum;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.cmomon.excepiton.BizException;
import cn.swallowff.admin.cmomon.excepiton.NoPermissionsException;
import cn.swallowff.admin.cmomon.excepiton.InvalidKaptchaException;
import cn.swallowff.admin.cmomon.excepiton.ServiceException;
import cn.swallowff.admin.modules.system.manager.logmgr.LogManager;
import cn.swallowff.admin.modules.system.manager.logmgr.factory.LogTaskFactory;
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
    public BaseResp serviceException(ServiceException e, HttpServletRequest request) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        request.setAttribute("tip", e.getMessage());
        log.error("业务异常:", e);
        return new BaseResp(e.getCode(), e.getErrorMessage());
    }

    /**
     * 拦截抛出的正常业务异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseResp bizException(ServiceException e, HttpServletRequest request) {
//        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(),e));
//        log.error("业务异常:",e);
        return new BaseResp(e.getCode(), e.getErrorMessage());
    }

    @ExceptionHandler(NoPermissionsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String noPermission(NoPermissionsException e, HttpServletRequest request) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
//        log.error("业务异常:",e);
//        return new BaseResp(e.getCode(),e.getErrorMessage());
        return "pages/error/404";
    }

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "pages/admin/login";
    }

    /**
     * 账号被冻结异常
     */
    @ExceptionHandler(AccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String account(AccountException e, Model model, HttpServletRequest request) {
        String account = request.getParameter("account");
        if (e instanceof DisabledAccountException) {
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "账号被冻结", HttpContext.getIp()));
            model.addAttribute("tips", "账号被冻结");
        } else if (e instanceof UnknownAccountException) {
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "账户不存在", HttpContext.getIp()));
            model.addAttribute("tips", "账户不存在");
        } else {
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "账户未知", HttpContext.getIp()));
            model.addAttribute("tips", "账户未知");
        }
        return "pages/admin/login";
    }


    /**
     * 密码错误异常
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model) {
        String account = HttpContext.getRequest().getParameter("account");
        if (e instanceof ExpiredCredentialsException) {
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "凭证过期", HttpContext.getIp()));
            model.addAttribute("tips", "凭证过期");
        } else if (e instanceof IncorrectCredentialsException) {
            LogManager.me().executeLog(LogTaskFactory.loginLog(account, "密码错误", HttpContext.getIp()));
            model.addAttribute("tips", "密码错误");
        }
        return "pages/admin/login";
    }

    /**
     * 验证码错误异常
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String kaptchaException(InvalidKaptchaException e, Model model) {
        String account = HttpContext.getRequest().getParameter("account");
        LogManager.me().executeLog(LogTaskFactory.loginLog(account, "验证码错误", HttpContext.getIp()));
        model.addAttribute("tips", "验证码错误");
        return "pages/admin/login";
    }

    /**
     * 无权访问该资源异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public BaseResp undeclaredThrowableException(UndeclaredThrowableException e) {
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
//        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
//        HttpContext.getRequest().setAttribute("tips", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new BaseResp(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMsg());
    }

    /**
     * 拦截未知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResp exception(Exception e) {
        ShiroUser user = ShiroKit.getUser();
        String userId = "";
        if (null != user) {
            userId = user.getId();
        }
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(userId, e));
        log.error("系统异常:", e);
        return new BaseResp(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMsg());
    }

}
