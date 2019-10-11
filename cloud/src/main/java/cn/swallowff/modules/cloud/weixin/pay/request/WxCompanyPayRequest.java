package cn.swallowff.modules.cloud.weixin.pay.request;


import cn.swallowff.common.json.JacksonUtil;

/**
 * @author Administrator
 * @description
 * @create 2019/5/30
 */
public class WxCompanyPayRequest extends WxPayBaseRequest {
    private String mchAppid;        //商户账号appid   yes
    private String deviceInfo;      //设备号          no
    private String partnerTradeNo;  //商户订单号      yes
    private String openid;          //用户openid      yes
    private String checkName;       //校验用户姓名选项 yes
    private String reUserName;      //收款用户姓名     no
    private int amount;             //金额  单位(分)
    private String desc;            //付款备注
    private String spbillCreateIp;  //ip地址
    private String mchid;           //商户号


    public String getMchAppid() {
        return mchAppid;
    }

    public void setMchAppid(String mchAppid) {
        this.mchAppid = mchAppid;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getReUserName() {
        return reUserName;
    }

    public void setReUserName(String reUserName) {
        this.reUserName = reUserName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
