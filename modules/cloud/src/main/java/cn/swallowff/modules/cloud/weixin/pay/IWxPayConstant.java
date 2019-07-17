package cn.swallowff.modules.cloud.weixin.pay;

/**
 * @author Administrator
 * @description
 * @create 2019/5/10
 */
public interface IWxPayConstant {
    String APPID = "wxe7bc8f965b98ed0e";   //公众号APPID 1
    String APPSECRET = "2d32ae10bc21472a5113c91bb32d9a7d";
    String PARTNERKEY = "jojiffwe84854osd98kf8998kd90023l";
    String MCHID = "1514874421";   //微信商户号

    String MP_APPID = "wx695388d28307d7d2";   //公众号APPID 2

    /**
     * weixin pay url
     */
    String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    String COMPANY_PAY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 付款时校验姓名
     */
    enum CheckName {
        NO_CHECK("NO_CHECK"),
        FORCE_CHECK("FORCE_CHECK");

        private String value;

        CheckName(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
