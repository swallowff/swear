package cn.swallowff.modules.demo.pay.weixin;

import cn.swallowff.common.framework.base.BaseResp;
import cn.swallowff.common.mapper.BeanMapConvert;

import java.util.Map;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public class WxMpPayment extends AbstractWxPayment {
    private WxMpPaymentRequest request;

    public WxMpPayment(WxMpPaymentRequest request,String partnerKey) {
        super(partnerKey);
        this.request = request;
    }

    @Override
    public BaseResp pay() {
        return super.pay();
    }

    @Override
    protected Map<String, Object> getParameterMap() {
        return BeanMapConvert.beanToMapObject(this.request);
    }

    @Override
    protected void paramConfig() {
        this.request.setPayType("微信公众号支付");
    }

//    @Override
//    protected String getPartnerkey() {
//        return "PartnerKey";
//    }
}
