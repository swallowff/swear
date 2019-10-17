package cn.swallowff.modules.demo.pay.weixin;

import cn.swallowff.common.framework.base.BaseResp;

import java.util.Map;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public class WxMiniPayment extends AbstractWxPayment {
    private WxMiniPaymentRequest request;

    public WxMiniPayment(WxMiniPaymentRequest request,String partnerKey) {
        super(partnerKey);
        this.request = request;
    }

    @Override
    public BaseResp pay() {
        return super.pay();
    }

    @Override
    protected Map<String, Object> getParameterMap() {
        return null;
    }

    @Override
    protected void paramConfig() {

    }

//    @Override
//    protected String getPartnerkey() {
//        return null;
//    }
}
