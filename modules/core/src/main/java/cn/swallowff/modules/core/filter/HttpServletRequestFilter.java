package cn.swallowff.modules.core.filter;

import cn.swallowff.modules.core.filter.wrapper.BodyReaderHttpRequestWrapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Administrator
 * @description
 * @create 2019/5/17
 */
public class HttpServletRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            // 只过滤json请求
            if (StringUtils.equalsIgnoreCase(servletRequest.getContentType(), "application/json")) {
                requestWrapper = new BodyReaderHttpRequestWrapper((HttpServletRequest) servletRequest);
            }
        }
        if (requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
