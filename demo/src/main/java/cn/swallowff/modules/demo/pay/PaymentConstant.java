package cn.swallowff.modules.demo.pay;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public interface PaymentConstant {
    String PARTNER_KEY = "PARTNER_KEY";

    enum Channel{
        WEIXIN(0),
        ALIPAY(1);

        private Integer code;

        Channel(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    enum PayType{
        WX_MP(0),
        WX_MINI(1),
        WX_H5(2),
        WX_BSC_SCAN(3),
        ALI_SERVICE(4),
        ALI_H5(5),
        ALI_BSC_SCAN(6);

        private Integer code;

        PayType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }
}
