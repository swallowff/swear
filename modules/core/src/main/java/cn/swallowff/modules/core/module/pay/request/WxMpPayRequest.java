package cn.swallowff.modules.core.module.pay.request;

/**
 * @author Administrator
 * @description 微信公众号支付请求参数
 * @create 2019/5/10
 */
public class WxMpPayRequest extends WxPayBaseRequest{
    private String attach;      //no 附加数据
    private String body;        //yes 商品描述
    private String notifyUrl;  //yes 回调地址
    private String outTradeNo; //yes 商户自定订单号
    private String spbillCreateIp;    //yes 终端ip
    private String totalFee;       //yes 金额(单位分)
    private String tradeType = "JSAPI"; //yes 交易类型
    private String openid;

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
