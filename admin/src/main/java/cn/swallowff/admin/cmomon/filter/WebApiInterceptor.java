package cn.swallowff.admin.cmomon.filter;

import cn.swallowff.common.json.JacksonUtil;
import cn.swallowff.common.mapper.XmlMapper;
import cn.swallowff.common.web.http.ServletUtils;
import cn.swallowff.admin.cmomon.filter.wrapper.RequestModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.MediaType;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (logger.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();//开始时间
            startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }
        RequestModel requestModel = new RequestModel();
        requestModel.setHttpMethod(request.getMethod());
        requestModel.setReqUri(request.getRequestURI());
        String contentType = request.getHeader("Content-Type");
        StringBuilder reqParams = new StringBuilder();
        reqParams.append(JacksonUtil.toJson(request.getParameterMap()));
        requestModel.setReqContentType(contentType);
        if (StringUtils.equalsAny(contentType,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_JSON_UTF8_VALUE,
                MediaType.TEXT_PLAIN_VALUE)) {
            String reqBody = ServletUtils.getRequestBody(request);
            reqParams.append("\nBody:");
            reqParams.append(reqBody);
        }
        requestModel.setReqParams(reqParams.toString());
        RequestModel.setRequestModel(requestModel);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            RequestModel.getRequestModel().setView(true);
            logger.info("ViewName: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        // 打印接口信息
        try {
            RequestModel requestModel = RequestModel.getRequestModel();
            if (logger.isDebugEnabled() && requestModel != null) {
                if (!requestModel.isView()) {  //页面请求不打印日志
                    logger.debug("\n\n==START==\n接口请求\nURI:{}\nMethod:{}\nContentType:{}\nParam:{}\n", requestModel.getReqUri(), requestModel.getHttpMethod(), requestModel.getReqContentType(), requestModel.getReqParams());
                    if (response != null) {
                        String responseContent = null;
                        if (StringUtils.containsAny(response.getContentType(), MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE)) {
                            responseContent = XmlMapper.toXml(requestModel.getRespParams(), true);
                        } else if (StringUtils.containsIgnoreCase(response.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
                            responseContent = JacksonUtil.toJson(requestModel.getRespParams());
                        }
                        logger.debug("\n接口响应\nContentType:{}\nBody:{}\n==THE END==", response.getContentType(), responseContent);
                    }
                }
                long beginTime = startTimeThreadLocal.get(); //得到线程绑定的局部变量（开始时间）
                long endTime = System.currentTimeMillis();    //结束时间
                logger.debug("计时结束：{}  耗时：{}  URI: {}  \n",
                        new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), (endTime - beginTime) + "ms", request.getRequestURI());
                //删除本地线程变量中的数据，防止内存泄漏
                startTimeThreadLocal.remove();
            }

        } finally {
            RequestModel.removeRequestModel();
        }
    }


}
