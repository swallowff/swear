package cn.swallowff.modules.demo.pay;

import cn.swallowff.modules.demo.pay.weixin.WxMiniPayment;
import cn.swallowff.modules.demo.pay.weixin.WxMiniPaymentRequest;
import cn.swallowff.modules.demo.pay.weixin.WxMpPayment;
import cn.swallowff.modules.demo.pay.weixin.WxMpPaymentRequest;

/**
 * @author shenyu
 * @create 2019/10/17
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public void create(PaymentCreateDto order) {
        if (null == order.getPayType()){
            throw new RuntimeException("order payType can't be null");
        }
        switch (order.getPayType()){
            case WX_MP:
                WxMpPaymentRequest request = new WxMpPaymentRequest(order.getOrderId(),order.getOpenid());
                new PaymentContext(new WxMpPayment(request,PaymentConstant.PARTNER_KEY)).executePay();
                break;
            case WX_MINI:
                WxMiniPaymentRequest request1 = new WxMiniPaymentRequest(order.getOrderId(),order.getOpenid());
                new PaymentContext(new WxMiniPayment(request1,PaymentConstant.PARTNER_KEY)).executePay();
                break;
            default:
                throw new RuntimeException("unSupported pay type : " + order.getPayType().name());
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void refund() {

    }

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        PaymentCreateDto order = new PaymentCreateDto();
        order.setOrderId("ORDER-100");
        order.setAmount(100);
        order.setOpenid("OPENN-ID-10086");
        order.setPayType(PaymentConstant.PayType.ALI_BSC_SCAN);
        orderService.create(order);
    }


}
