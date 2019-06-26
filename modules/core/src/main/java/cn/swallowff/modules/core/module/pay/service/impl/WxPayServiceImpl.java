package cn.swallowff.modules.core.module.pay.service.impl;

import cn.swallowff.modules.core.module.pay.exception.WxPayException;
import cn.swallowff.modules.core.module.pay.request.WxCompanyPayRequest;
import cn.swallowff.modules.core.module.pay.request.WxMpPayRequest;
import cn.swallowff.modules.core.module.pay.response.WxCompanyPayResponse;
import cn.swallowff.modules.core.module.pay.response.WxMpPayResponse;
import cn.swallowff.modules.core.module.pay.response.WxPayBaseResponse;
import cn.swallowff.modules.core.module.pay.service.AbstractWxPay;
import cn.swallowff.modules.core.module.pay.service.IWxPay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description
 * @create 2019/5/10
 */
@Service("wxPayServiceImpl")
public class WxPayServiceImpl extends AbstractWxPay implements IWxPay {
    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public WxMpPayResponse mpPay(WxMpPayRequest wxMpPayRequest) throws WxPayException {
        WxMpPayResponse response = super.post(PAY_URL,wxMpPayRequest,WxMpPayResponse.class);
        return respHandle(response);
    }

    @Override
    public WxMpPayResponse miniProgramPay(WxMpPayRequest wxMpPayRequest) throws WxPayException{
        WxMpPayResponse response = super.post(PAY_URL,wxMpPayRequest,WxMpPayResponse.class);
        return respHandle(response);
    }

    @Override
    public WxPayBaseResponse h5Pay() {
        return null;
    }

    @Override
    public WxCompanyPayResponse companyPay(WxCompanyPayRequest wxCompanyPayRequest,String partnerKey) throws WxPayException{
        WxCompanyPayResponse response = super.postSSL(COMPANY_PAY_URL,partnerKey,wxCompanyPayRequest,WxCompanyPayResponse.class);
        return respHandle(response);
    }

    @Override
    public <T> T respHandle(WxPayBaseResponse response) throws WxPayException {
        if (!response.isReturnSuccess()){
            throw new WxPayException(response.getReturnMsg());
        }else if (!response.isResultSuccess()){
            throw new WxPayException(response.getErrCode() + ":" + response.getErrCodeDes());
        }else {
            return (T) response;
        }
    }

}
