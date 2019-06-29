package cn.swallowff.modules.core.cmomon.resp;

import cn.swallowff.modules.core.constant.states.ResponseState;

/**
 * @author shenyu
 * @create 19-6-27
 */
public class LayPageResp<T> extends BaseResp {
    private long count;  //总数据

    public LayPageResp(Integer code, String msg) {
        super(code, msg);
    }

    public LayPageResp(ResponseState respResponseState) {
        super(respResponseState);
    }

    public static LayPageResp newSuccess(){
        return new LayPageResp(ResponseState.LAYUI_TABLE_AJAX_SUCCESS);
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
