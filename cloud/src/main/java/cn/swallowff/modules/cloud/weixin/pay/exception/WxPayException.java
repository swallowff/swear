package cn.swallowff.modules.cloud.weixin.pay.exception;

/**
 * @author Administrator
 * @description
 * @create 2019/5/30
 */
public class WxPayException extends RuntimeException {
    public WxPayException() {
        super();
    }

    public WxPayException(String message) {
        super(message);
    }
}
