package cn.swallowff.modules.demo.pay;

import cn.swallowff.common.framework.base.BaseResp;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public class PaymentContext {
    private Payment payment;

    public PaymentContext(Payment payment) {
        this.payment = payment;
    }

    public BaseResp executePay(){
        return payment.pay();
    }
}
