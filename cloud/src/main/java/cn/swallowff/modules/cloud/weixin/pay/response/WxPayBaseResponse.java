package cn.swallowff.modules.cloud.weixin.pay.response;

import java.io.Serializable;

/**
 * @author Administrator
 * @description
 * @create 2019/5/9
 */
public class WxPayBaseResponse<T extends WxPayBaseResponse> implements Serializable {
    private String returnCode;
    private String returnMsg;

    //returnCode=SUCCESS时具有以下字段
    private String resultCode;
    private String errCode;
    private String errCodeDes;

    public boolean isReturnSuccess() {
        return "SUCCESS".equalsIgnoreCase(returnCode);
    }

    public boolean isResultSuccess() {
        if (isReturnSuccess()) {
            return "SUCCESS".equalsIgnoreCase(resultCode);
        } else return false;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
