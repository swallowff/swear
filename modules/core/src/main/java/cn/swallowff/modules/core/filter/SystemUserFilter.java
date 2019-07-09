package cn.swallowff.modules.core.filter;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.shiro.ShiroKit;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import sun.security.provider.certpath.OCSPResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * 使用该过滤器拦截ajax请求, 同时解决session超时的问题
 */
public class SystemUserFilter extends AccessControlFilter {

    /**
     * 已登录或user存在的,放行
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    /**
     * 未获取到subject时执行
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

        /**
         * 如果是ajax请求则不进行跳转
         */
        if (httpServletRequest.getHeader("x-requested-with") != null
                && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //可以返回BaseResp格式错误信息
            BaseResp baseResp = new BaseResp(ResponseState.SESSION_TIME_OUT);
            httpServletResponse.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                writer = httpServletResponse.getWriter();
                writer.write(baseResp.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != writer){
                    writer.close();
                }
            }
            return false;
        } else {

            /**
             * 第一次点击页面
             */
            String referer = httpServletRequest.getHeader("Referer");
            if (referer == null) {
                saveRequestAndRedirectToLogin(request, response);
                return false;
            } else {
                /**
                 * 从别的页面跳转过来的
                 */
                if (ShiroKit.getSession().getAttribute("sessionFlag") == null) {
                    httpServletRequest.getRequestDispatcher("/a/login/login.html").forward(request, response);
                    return false;
                } else {
                    saveRequestAndRedirectToLogin(request, response);
                    return false;
                }
            }
        }
    }
}
