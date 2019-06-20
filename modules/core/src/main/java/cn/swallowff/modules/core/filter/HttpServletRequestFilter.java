package cn.swallowff.modules.core.filter;

import cn.swallowff.modules.core.filter.wrapper.BodyReaderHttpRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

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
            // 过滤有body的请求
            if (StringUtils.equalsAnyIgnoreCase(servletRequest.getContentType(), MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_XML_VALUE)) {
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
