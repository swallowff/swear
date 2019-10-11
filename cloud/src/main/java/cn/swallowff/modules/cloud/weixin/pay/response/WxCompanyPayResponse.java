package cn.swallowff.modules.cloud.weixin.pay.response;

/**
 * @author Administrator
 * @description
 * @create 2019/5/30
 */
public class WxCompanyPayResponse extends WxPayBaseResponse<WxCompanyPayResponse> {
    //以下字段在return_code为SUCCESS时有返回
    private String mchAppid;
    private String mchid;
    private String deviceInfo;
    private String nonceStr;

    //以下字段在return_code和result_code都为SUCCESS的时候有返回
    private String partnerTradeNo;
    private String paymentNo;
    private String paymentTime;

    public String getMchAppid() {
        return mchAppid;
    }

    public void setMchAppid(String mchAppid) {
        this.mchAppid = mchAppid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
}
