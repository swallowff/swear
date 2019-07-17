package cn.swallowff.modules.cloud.weixin.pay;

import cn.swallowff.common.mapper.BeanMapConvert;
import cn.swallowff.common.mapper.XmlMapper;
import cn.swallowff.modules.cloud.weixin.pay.request.WxPayBaseRequest;
import cn.swallowff.modules.cloud.weixin.pay.response.WxPayBaseResponse;
import cn.swallowff.modules.core.util.PooledHttpClientAdaptor;
import cn.swallowff.modules.core.util.WxUtils;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * @author Administrator
 * @description
 * @create 2019/5/9
 */
public abstract class AbstractWxPay implements IWxPay {
    protected Logger logger = getLogger();

    @Resource(name = "defaultHttpClientPool")
    private PooledHttpClientAdaptor httpClientPool;

    @Resource(name = "wxSSLHttpClientPool")
    private PooledHttpClientAdaptor sslHttpClientPool;

    /**
     * 发送普通HTTP/HTTPS请求
     *
     * @param url
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> T post(String url, WxPayBaseRequest request, Class<? extends WxPayBaseResponse> clazz) {
        Map<String, Object> reqMap = BeanMapConvert.beanToMapObject(request, true);
        String sign = WxUtils.getSign(reqMap, "");
        reqMap.put("sign", sign);
        String xmlReq = XmlMapper.toXml(reqMap, "xml");
        String respStr = httpClientPool.doPost(url, null, xmlReq);
        return parseXmlResp(respStr, clazz);
    }

    /**
     * 使用微信的自定义证书发送加密请求
     *
     * @param url
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> T postSSL(String url, String partnerKey, WxPayBaseRequest request, Class<? extends WxPayBaseResponse> clazz) {
        Map<String, Object> reqMap = BeanMapConvert.beanToMapObject(request, true);
        String sign = WxUtils.getSign(reqMap, partnerKey);
        reqMap.put("sign", sign);
        String xmlReq = XmlMapper.toXml(reqMap, "xml");
        String respStr = sslHttpClientPool.doPost(url, Collections.EMPTY_MAP, xmlReq);
        return parseXmlResp(respStr, clazz);
    }

    private <T> T parseXmlResp(String respStr, Class<? extends WxPayBaseResponse> clazz) {
        T t = null;
        try {
            t = (T) BeanMapConvert.mapToBean(XmlMapper.xmlToMap(respStr, false), clazz.newInstance(), true);
//            t = XmlMapper.fromXml(respStr,clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }

    protected boolean checkSign(WxPayBaseResponse wxPayBaseResponse) {
        return true;
    }

    protected abstract Logger getLogger();

}
