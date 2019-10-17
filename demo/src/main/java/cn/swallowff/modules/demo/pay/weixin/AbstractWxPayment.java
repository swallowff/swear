package cn.swallowff.modules.demo.pay.weixin;

import cn.swallowff.common.framework.base.BaseResp;
import cn.swallowff.common.json.JacksonUtil;
import cn.swallowff.common.utils.WxUtils;
import cn.swallowff.modules.demo.pay.Payment;

import java.util.Map;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public abstract class AbstractWxPayment implements Payment {
    protected String partnerKey;

    protected AbstractWxPayment(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    @Override
    public BaseResp pay() {
        Map<String,Object> reqMap = signedParameterMap();
        System.out.println("微信支付成功:"+ JacksonUtil.toJson(reqMap));
        return BaseResp.newSuccess(reqMap);
    }

    private Map<String,Object> signedParameterMap(){
        paramConfig();
        Map<String,Object> map = getParameterMap();
        String sign = WxUtils.getSign(map,partnerKey);
        map.put("sign",sign);
        return map;
    }

    protected abstract Map<String,Object> getParameterMap();

    protected abstract void paramConfig();

//    protected abstract String getPartnerkey();
}
