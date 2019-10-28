package cn.swallowff.admin.cmomon.filter.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author Administrator
 * @description 解决从request中获取body数据后, 数据丢失的问题
 * @create 2019/5/15
 */
public class BodyReaderHttpRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] bytes;

    public BodyReaderHttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        bytes = baos.toByteArray();

//        String body = new String(bytes);
//        System.out.println(body);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}
