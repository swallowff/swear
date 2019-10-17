package cn.swallowff.modules.demo.pay;

public interface OrderService {

    void create(PaymentCreateDto order);

    void cancel();

    void refund();

}
