package cn.swallowff.modules.core.filter.wrapper;

import java.util.Date;

/**
 * @author Administrator
 * @description
 * @create 2019/5/15
 */
public class RequestModel {
    private static final ThreadLocal<RequestModel> REQUEST_MODEL = new ThreadLocal<>();

    private String platform;    //平台
    private String version;     //版本
    private String product;     //产品
    private Long startMillis;   //开始时间
    private String sessionId;   //当前请求用户sessionId
    private String reqUri;      //请求地址
    private String reqParams;   //Json串
    private String reqContentType;  //请求的contentType
    private Object respParams;  //返回值
    private String respContentType; //返回的contentType
    private String httpMethod;  //HTTP-POST
    private Date reqTime;       //请求时间
    private boolean isView;     //是否是页面请求

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getStartMillis() {
        return startMillis;
    }

    public void setStartMillis(Long startMillis) {
        this.startMillis = startMillis;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }

    public String getReqUri() {
        return reqUri;
    }

    public void setReqUri(String reqUri) {
        this.reqUri = reqUri;
    }

    public Object getRespParams() {
        return respParams;
    }

    public void setRespParams(Object respParams) {
        this.respParams = respParams;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public static RequestModel getRequestModel() {
        return REQUEST_MODEL.get();
    }

    public static void setRequestModel(RequestModel request) {
        REQUEST_MODEL.set(request);
    }

    public static void removeRequestModel() {
        REQUEST_MODEL.remove();
    }

    public String getReqContentType() {
        return reqContentType;
    }

    public void setReqContentType(String reqContentType) {
        this.reqContentType = reqContentType;
    }

    public String getRespContentType() {
        return respContentType;
    }

    public void setRespContentType(String respContentType) {
        this.respContentType = respContentType;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }
}
