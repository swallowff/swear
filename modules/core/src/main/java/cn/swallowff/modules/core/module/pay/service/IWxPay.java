package cn.swallowff.modules.core.module.pay.service;

import cn.swallowff.modules.core.module.pay.exception.WxPayException;
import cn.swallowff.modules.core.module.pay.request.WxCompanyPayRequest;
import cn.swallowff.modules.core.module.pay.request.WxMpPayRequest;
import cn.swallowff.modules.core.module.pay.response.WxCompanyPayResponse;
import cn.swallowff.modules.core.module.pay.response.WxMpPayResponse;
import cn.swallowff.modules.core.module.pay.response.WxPayBaseResponse;

/**
 * @author shenyu
 * @description 微信支付接口
 * @create 2019/5/9
 */
public interface IWxPay extends IWxPayConstant{

    //公众号支付
    WxMpPayResponse mpPay(WxMpPayRequest wxMpPayRequest) throws WxPayException;

    //小程序支付
    WxMpPayResponse miniProgramPay(WxMpPayRequest wxMpPayRequest) throws WxPayException;

    //H5支付
    WxPayBaseResponse h5Pay() throws WxPayException;

    //企业付款
    WxCompanyPayResponse companyPay(WxCompanyPayRequest wxCompanyPayRequest,String partnerKey) throws WxPayException;

    <T> T respHandle(WxPayBaseResponse response) throws WxPayException;

}
