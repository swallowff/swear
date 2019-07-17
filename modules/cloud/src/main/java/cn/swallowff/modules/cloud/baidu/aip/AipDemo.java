package cn.swallowff.modules.cloud.baidu.aip;

import cn.swallowff.common.io.FileUtils;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 * @description
 * @create 2019/7/17
 */
public class AipDemo {

    //设置APPID/AK/SK
    public static final String APP_ID = "16822710";
    public static final String API_KEY = "Vg4DAATqGK0c6db9jZMnVGDo";
    public static final String SECRET_KEY = "rqB8DUiUmTrQtYBjiXQ2NwUw0BkRYoRD";

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "classpath:/log4j.properties");

        // 调用接口
        TtsResponse res = client.synthesis("对不起,您呼叫的用户正在通话中,请稍后再拨", "zh", 1, null);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                FileUtils.createFile("/data/upload/mp3/output.mp3");
                FileUtils.writeByteArrayToFile(new File("/data/upload/mp3/output.mp3"), data);
//                Util.writeBytesToFileSystem(data, "output.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }

    }
}
