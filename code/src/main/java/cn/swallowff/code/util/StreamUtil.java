package cn.swallowff.code.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 流工具类
 */
public class StreamUtil {

    public static void close(Closeable... closeables){
        for (Closeable c : closeables){
            if (null != c){
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
