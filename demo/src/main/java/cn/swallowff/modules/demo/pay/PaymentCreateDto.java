package cn.swallowff.modules.demo.pay;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public class PaymentCreateDto {
    private String orderId;
    private PaymentConstant.Channel channel;
    private PaymentConstant.PayType payType;
    private Integer amount;
    private String openid;      //微信openid
    private String appCode;     //支付宝appCode

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentConstant.Channel getChannel() {
        return channel;
    }

    public void setChannel(PaymentConstant.Channel channel) {
        this.channel = channel;
    }

    public PaymentConstant.PayType getPayType() {
        return payType;
    }

    public void setPayType(PaymentConstant.PayType payType) {
        this.payType = payType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
