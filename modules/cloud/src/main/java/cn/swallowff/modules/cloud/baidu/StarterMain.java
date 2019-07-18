package cn.swallowff.modules.cloud.baidu;

import cn.swallowff.modules.cloud.baidu.aip.BaiduAipConst;
import cn.swallowff.modules.cloud.baidu.aip.BaiduAudioTransfer;

/**
 * @author shenyu
 * @create 2019/7/18
 */
public class StarterMain {

    public static void main(String[] args){
        BaiduAudioTransfer transfer = new BaiduAudioTransfer(BaiduAipConst.APP_ID,BaiduAipConst.API_KEY,BaiduAipConst.SECRET_KEY);
        transfer.synthesisAudio("晚上好,大佬");
    }
}
