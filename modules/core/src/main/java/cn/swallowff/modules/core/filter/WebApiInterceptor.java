package cn.swallowff.modules.core.filter;

import cn.swallowff.common.json.JacksonUtil;
import cn.swallowff.common.web.http.ServletUtils;
import cn.swallowff.modules.core.filter.wrapper.RequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * @description 打印接口请求参数
 * @create 2019/5/14
 */
public class WebApiInterceptor implements HandlerInterceptor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
    private static final String X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String APPLICATION_JSON = "application/json";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (logger.isDebugEnabled()){
            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）
            logger.info("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }
        RequestModel requestModel = new RequestModel();
        requestModel.setHttpMethod(request.getMethod());
        requestModel.setReqUri(request.getRequestURI());
        String contentType = request.getHeader("Content-Type");
        if (X_WWW_FORM_URLENCODED.equals(contentType)){
            requestModel.setReqParams(JacksonUtil.toJson(request.getParameterMap()));
        }else if (MULTIPART_FORM_DATA.equals(contentType)){

        }else if (APPLICATION_JSON.equals(contentType)){
            String reqBody = ServletUtils.getRequestBody(request);
            requestModel.setReqParams(reqBody);
        }
        RequestModel.setRequestModel(requestModel);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null){
            logger.info("ViewName: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        // 打印接口信息。
        try {
            RequestModel requestModel = RequestModel.getRequestModel();
            if (logger.isDebugEnabled() && requestModel != null) {
                logger.info("\n===>\nrequest URI:{}\nrequest Method:{}\nrequest parameter:{}\n===>",requestModel.getReqUri(),requestModel.getHttpMethod(),requestModel.getReqParams());
                if (response != null) {
                    logger.info("\n===>\nresponse parameter:{}\n===>", JacksonUtil.toJson(requestModel.getRespParams()));
                }
                long beginTime = startTimeThreadLocal.get(); //得到线程绑定的局部变量（开始时间）
                long endTime = System.currentTimeMillis();    //2、结束时间
                logger.info("计时结束：{}  耗时：{}  URI: {}  ",
                        new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), (endTime - beginTime) + "ms", request.getRequestURI());
                //删除线程变量中的数据，防止内存泄漏
                startTimeThreadLocal.remove();
            }

        } finally {
            RequestModel.removeRequestModel();
        }
    }


}
