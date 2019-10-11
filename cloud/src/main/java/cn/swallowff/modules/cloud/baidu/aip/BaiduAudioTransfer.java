package cn.swallowff.modules.cloud.baidu.aip;

import cn.swallowff.common.io.FileUtils;
import cn.swallowff.common.lang.StringUtils;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author shenyu
 * @create 2019/7/18
 */
public class BaiduAudioTransfer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private AipSpeech aipSpeech;
    private String targetPath = "/data/upload/mp3/";
    private String suffix = ".mp3";

    public BaiduAudioTransfer(String appid,String appKey,String secretKey,String targetPath,String suffix) {
        AipSpeech aipSpeech = new AipSpeech(appid,appKey,secretKey);
        aipSpeech.setConnectionTimeoutInMillis(2000);
        aipSpeech.setSocketTimeoutInMillis(60000);
        this.aipSpeech = aipSpeech;
        this.targetPath = targetPath;
        this.suffix = suffix;
    }

    public BaiduAudioTransfer(String appid,String appKey,String secretKey,String targetPath) {
        AipSpeech aipSpeech = new AipSpeech(appid,appKey,secretKey);
        aipSpeech.setConnectionTimeoutInMillis(2000);
        aipSpeech.setSocketTimeoutInMillis(60000);
        this.aipSpeech = aipSpeech;
        this.targetPath = targetPath;
    }

    public BaiduAudioTransfer(String appid,String appKey,String secretKey) {
        AipSpeech aipSpeech = new AipSpeech(appid,appKey,secretKey);
        aipSpeech.setConnectionTimeoutInMillis(2000);
        aipSpeech.setSocketTimeoutInMillis(60000);
        this.aipSpeech = aipSpeech;
    }

    public File synthesisAudio(String text){
        TtsResponse res = aipSpeech.synthesis(text, "zh", 1, null);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        String fileName = System.currentTimeMillis() + StringUtils.getRandomStr(8) + suffix;
        String var1 = targetPath + fileName;
        if (data != null) {
            try {
                File file = FileUtils.createFile2(var1);
                if (file != null){
                    FileUtils.writeByteArrayToFile(new File(var1), data);
                    return file;
                }else {
                    throw new RuntimeException("synthesis audio failed");
                }
//                Util.writeBytesToFileSystem(data, "output.mp3");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        if (res1 != null) {
            logger.error(res1.toString(2));
        }
        return null;
    }


}
