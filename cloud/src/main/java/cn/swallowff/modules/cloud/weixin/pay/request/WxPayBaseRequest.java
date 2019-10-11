package cn.swallowff.modules.cloud.weixin.pay.request;

/**
 * @author Administrator
 * @description
 * @create 2019/5/9
 */
public class WxPayBaseRequest {
    private String appid;
    private String mchId;
    private String nonceStr;
    private String sign;
//    private String timestamp;


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
