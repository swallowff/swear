package cn.swallowff.admin.util;

import cn.swallowff.admin.cmomon.constant.exceptionenum.CoreExceptionEnum;
import cn.swallowff.admin.cmomon.excepiton.ServiceException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shenyu
 * @create 19-5-22
 */
public class CommonUtil {

    public static byte[] toByteArray(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            throw new ServiceException(CoreExceptionEnum.FILE_NOT_FOUND);
        } else {
            FileChannel channel = null;
            FileInputStream fs = null;

            try {
                fs = new FileInputStream(f);
                channel = fs.getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());

                while (channel.read(byteBuffer) > 0) {
                    ;
                }

                byte[] var5 = byteBuffer.array();
                return var5;
            } catch (IOException var17) {
                throw new ServiceException(CoreExceptionEnum.FILE_READING_ERROR);
            } finally {
                try {
                    channel.close();
                } catch (IOException var16) {
                    throw new ServiceException(CoreExceptionEnum.FILE_READING_ERROR);
                }

                try {
                    fs.close();
                } catch (IOException var15) {
                    throw new ServiceException(CoreExceptionEnum.FILE_READING_ERROR);
                }
            }
        }
    }

    public static String getExceptionMsg(Throwable e) {
        StringWriter sw = new StringWriter();

        try {
            e.printStackTrace(new PrintWriter(sw));
        } finally {
            try {
                sw.close();
            } catch (IOException var8) {
                var8.printStackTrace();
            }

        }

        return sw.getBuffer().toString().replaceAll("\\$", "T");
    }
}
