package cn.swallowff.modules.demo.pay.weixin;

import cn.swallowff.modules.demo.pay.PaymentRequest;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public class WxMpPaymentRequest implements PaymentRequest {
    private String orderId;
    private String openid;
    private String payType;

    public WxMpPaymentRequest(String orderId, String openid) {
        this.orderId = orderId;
        this.openid = openid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "WxPaymentRequest{" +
                "orderId='" + orderId + '\'' +
                ", openid='" + openid + '\'' +
                ", payType='" + payType + '\'' +
                '}';
    }


}
