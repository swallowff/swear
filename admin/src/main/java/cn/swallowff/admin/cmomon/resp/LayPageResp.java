package cn.swallowff.admin.cmomon.resp;

import cn.swallowff.admin.cmomon.constant.states.ResponseState;

import java.util.List;

/**
 * @author shenyu
 * @create 19-6-27
 */
public class LayPageResp<T> extends BaseResp {
    private long count;  //总数据

    public LayPageResp(List<T> dataList, long count) {
        super(ResponseState.SUCCESS);
        this.setData(dataList);
        this.setCount(count);
    }

    public LayPageResp(Integer code, String msg) {
        super(code, msg);
    }

    public LayPageResp(ResponseState respResponseState) {
        super(respResponseState);
    }

    public static LayPageResp newSuccess() {
        return new LayPageResp(ResponseState.SUCCESS);
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
