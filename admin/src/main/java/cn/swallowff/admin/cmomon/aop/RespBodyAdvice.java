package cn.swallowff.admin.cmomon.aop;

import cn.swallowff.admin.cmomon.filter.wrapper.RequestModel;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Administrator
 * @description 拦截response aop
 * @create 2019/5/15
 */
@ControllerAdvice
@Component
public class RespBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 注意，这里必须返回true，否则不会执行beforeBodyWrite方法
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        //获取在WebApiInterceptor对指定请求参数放如到线程局部变量的对象
        RequestModel model = RequestModel.getRequestModel();
        if (null != model) {
            model.setRespParams(body);
        }
        return body;
    }
}
