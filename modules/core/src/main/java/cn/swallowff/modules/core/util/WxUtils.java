package cn.swallowff.modules.core.util;

import cn.swallowff.common.codec.EncryptAndDecryptUtils;

import java.util.*;

/**
 * @author Administrator
 * @description
 * @create 2019/6/10
 */
public class WxUtils {

    //生成签名
    public static String getSign(Map<String,Object> paramsMap,String partnerkey){
        String result = "";
        try {
            List<Map.Entry<String, Object>> entryList = new ArrayList<Map.Entry<String, Object>>(paramsMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(entryList, new Comparator<Map.Entry<String, Object>>() {
                public int compare(Map.Entry<String, Object> entry1, Map.Entry<String, Object> entry2) {
                    return (entry1.getKey()).compareTo(entry2.getKey());
                }
            });
            // 构造签名键值对字符串的格式
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, Object> item : entryList) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    Object val = item.getValue();
                    // 过滤空值和sign
                    if (!("".equals(val) || val == null || "sign".equals(key) )) {
                        sb.append(key + "=" + val + "&");
                    }
                }
            }
            sb.append("key="+partnerkey);

            //MD5加密
            result = EncryptAndDecryptUtils.md5Encrypt(sb.toString());
            result = result.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

//    public static void main(String[] args){
//        System.out.println(EncryptAndDecryptUtils.md5Encrypt("appid=wx8e08b1b2d67b05b0&attach=218104094246878454&body=合业联盟收款218104094246878454&mch_id=1514874421&nonce_str=2147483647&notify_url=https://miniapp.98htcz.com:8443//yiye/pay/wxNotify&openid=otE3I5eSqwyAO2fsGsmTzxEMaXiM&out_trade_no=201906121424271806&spbill_create_ip=117.136.30.213&total_fee=1&trade_type=JSAPI&key=jojiffwe84854osd98kf8998kd90023l"));
//    }

}
