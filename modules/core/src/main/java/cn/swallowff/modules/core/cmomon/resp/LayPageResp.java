package cn.swallowff.modules.core.cmomon.resp;

import cn.swallowff.modules.core.constant.states.ResponseState;

/**
 * @author shenyu
 * @create 19-6-27
 */
public class LayPageResp extends BaseResp {
    private Integer count;

    public LayPageResp(Integer code, String msg) {
        super(code, msg);
    }

    public LayPageResp(ResponseState respResponseState) {
        super(respResponseState);
    }

    public static LayPageResp newSuccess(){
        return new LayPageResp(ResponseState.LAYUI_TABLE_AJAX_SUCCESS);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
