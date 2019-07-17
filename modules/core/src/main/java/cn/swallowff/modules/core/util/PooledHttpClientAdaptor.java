package cn.swallowff.modules.core.util;

import cn.swallowff.common.json.JacksonUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * httpClient连接池适配器
 *
 * @author shenyu
 * @create 2019/5/9
 */
public class PooledHttpClientAdaptor {
    private final Logger logger = LoggerFactory.getLogger(PooledHttpClientAdaptor.class);

    private static final int DEFAULT_POOL_MAX_TOTAL = 200;
    private static final int DEFAULT_POOL_MAX_PER_ROUTE = 200;

    private static final int DEFAULT_CONNECT_TIMEOUT = 500;
    private static final int DEFAULT_CONNECT_REQUEST_TIMEOUT = 500;
    private static final int DEFAULT_SOCKET_TIMEOUT = 2000;

    private PoolingHttpClientConnectionManager gcm = null;

    private CloseableHttpClient httpClient = null;

    private IdleConnectionMonitorThread idleThread = null;

    // 连接池的最大连接数
    private final int maxTotal;
    // 连接池按route配置的最大连接数
    private final int maxPerRoute;

    // tcp connect的超时时间
    private final int connectTimeout;
    // 从连接池获取连接的超时时间
    private final int connectRequestTimeout;
    // tcp io的读写超时时间
    private final int socketTimeout;

    public PooledHttpClientAdaptor() {
        this(
                PooledHttpClientAdaptor.DEFAULT_POOL_MAX_TOTAL,
                PooledHttpClientAdaptor.DEFAULT_POOL_MAX_PER_ROUTE,
                PooledHttpClientAdaptor.DEFAULT_CONNECT_TIMEOUT,
                PooledHttpClientAdaptor.DEFAULT_CONNECT_REQUEST_TIMEOUT,
                PooledHttpClientAdaptor.DEFAULT_SOCKET_TIMEOUT
        );
    }

    public PooledHttpClientAdaptor(int maxTotal, int maxPerRoute, int connectTimeout, int connectRequestTimeout, int socketTimeout) {

        this.maxTotal = maxTotal;
        this.maxPerRoute = maxPerRoute;
        this.connectTimeout = connectTimeout;
        this.connectRequestTimeout = connectRequestTimeout;
        this.socketTimeout = socketTimeout;

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        this.gcm = new PoolingHttpClientConnectionManager(registry);
        this.gcm.setMaxTotal(this.maxTotal);
        this.gcm.setDefaultMaxPerRoute(this.maxPerRoute);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(this.connectTimeout)                     // 设置连接超时
                .setSocketTimeout(this.socketTimeout)                       // 设置读取超时
                .setConnectionRequestTimeout(this.connectRequestTimeout)    // 设置从连接池获取连接实例的超时
                .build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClient = httpClientBuilder
                .setConnectionManager(this.gcm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        idleThread = new IdleConnectionMonitorThread(this.gcm);
        idleThread.start();
    }

    /**
     * 自定义加密httpClient构造方法
     *
     * @param certFile
     * @param maxTotal
     * @param maxPerRoute
     * @param connectTimeout
     * @param connectRequestTimeout
     * @param socketTimeout
     */
    public PooledHttpClientAdaptor(File certFile, String password, int maxTotal, int maxPerRoute, int connectTimeout, int connectRequestTimeout, int socketTimeout) {
        SSLConnectionSocketFactory sslfactory = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(certFile);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, password.toCharArray());
            SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
            sslfactory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, new DefaultHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        this.maxTotal = maxTotal;
        this.maxPerRoute = maxPerRoute;
        this.connectTimeout = connectTimeout;
        this.connectRequestTimeout = connectRequestTimeout;
        this.socketTimeout = socketTimeout;

        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        registryBuilder.register("http", PlainConnectionSocketFactory.getSocketFactory());
        if (null != sslfactory) {
            registryBuilder.register("https", sslfactory);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();

        this.gcm = new PoolingHttpClientConnectionManager(registry);
        this.gcm.setMaxTotal(this.maxTotal);
        this.gcm.setDefaultMaxPerRoute(this.maxPerRoute);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(this.connectTimeout)                     // 设置连接超时
                .setSocketTimeout(this.socketTimeout)                       // 设置读取超时
                .setConnectionRequestTimeout(this.connectRequestTimeout)    // 设置从连接池获取连接实例的超时
                .build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClient = httpClientBuilder
                .setConnectionManager(this.gcm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        idleThread = new IdleConnectionMonitorThread(this.gcm);
        idleThread.start();
    }

    public String doGet(String url) {
        return this.doGet(url, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
    }

    public String doGet(String url, Map<String, Object> params) {
        return this.doGet(url, Collections.EMPTY_MAP, params);
    }

    public String doGet(String url, Map<String, String> headers, Map<String, Object> params) {

        // 构建GET请求头
        String apiUrl = getUrlWithParams(url, params);
        StringBuilder logStr = new StringBuilder();
        logStr.append("\n===HTTP请求===").append("\nMethod:").append("GET")
                .append("\nURL:").append(apiUrl).append("\nHeaders:").append(JacksonUtil.toJson(headers));

        HttpGet httpGet = new HttpGet(apiUrl);

        // 设置header信息
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            logStr.append("\n===HTTP响应===").append("\n状态码:").append(statusCode);

//            logger.info("\n--http-response--响应状态码:"+statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    String result = EntityUtils.toString(entityRes, "UTF-8");
                    logStr.append("\n响应内容: ").append(result);
//                    logger.info("\n--http-response--响应内容:\n"+result);
                    return result;
                }
            }
            return null;
        } catch (IOException e) {
        } finally {
            logger.info("HTTP log Messages:{}", logStr);
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public String doPost(String apiUrl, Map<String, Object> params) {
        return this.doPost(apiUrl, Collections.EMPTY_MAP, params);
    }

    public String doPost(String apiUrl, Map<String, String> headers, String reqParams) {
        StringBuilder logStr = new StringBuilder();
        logStr.append("\n===HTTP请求===").append("\nMethod:").append("POST")
                .append("\nURL:").append(apiUrl).append("\nHeaders:").append(JacksonUtil.toJson(headers))
                .append("\nParams:").append(reqParams);

        HttpPost httpPost = new HttpPost(apiUrl);

        // 配置请求headers
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 配置请求参数
//        if ( params != null && params.size() > 0 ) {
        HttpEntity httpEntity = new StringEntity(reqParams, Charset.forName("UTF-8"));
//            HttpEntity entityReq = getUrlEncodedFormEntity(params);
        httpPost.setEntity(httpEntity);
//        }


        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            logStr.append("\n===HTTP响应===").append("\n状态码:").append(statusCode);
//            logger.info("\n--http-response--响应状态码:"+statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    String result = EntityUtils.toString(entityRes, "UTF-8");
//                    logger.info("\n--http-response--响应内容: \n"+result);
                    logStr.append("\n响应内容:").append(result);
                    return result;
                }
            }
            return null;
        } catch (IOException e) {
        } finally {
            logger.info("HTTP log Messages:{}", logStr);
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public String doPost(String apiUrl, Map<String, String> headers, Map<String, Object> params) {
        StringBuilder logStr = new StringBuilder();
        logStr.append("\n===HTTP请求===").append("\nMethod:").append("POST")
                .append("\nURL:").append(apiUrl).append("\nHeaders:").append(JacksonUtil.toJson(headers))
                .append("\nParams:").append(JacksonUtil.toJson(params));

        HttpPost httpPost = new HttpPost(apiUrl);

        // 配置请求headers
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 配置请求参数
        if (params != null && params.size() > 0) {
            HttpEntity entityReq = getUrlEncodedFormEntity(params);
            httpPost.setEntity(entityReq);
        }


        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            logStr.append("\n===HTTP响应===").append("\n状态码:").append(statusCode);
//            logger.info("\n--http-response--响应状态码:"+statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    String result = EntityUtils.toString(entityRes, "UTF-8");
                    logStr.append("\n响应内容 :").append(result);
//                    logger.info("\n--http-response--响应内容 :\n"+result);
                    return result;
                }
            }
            return null;
        } catch (IOException e) {
        } finally {
            logger.info("HTTP log Messages:{}", logStr);
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
        return null;

    }

    public String doPostBase64(String apiUrl, Map<String, String> headers, String reqParams) {
        HttpPost httpPost = new HttpPost(apiUrl);

        // 配置请求headers
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 配置请求参数
        HttpEntity httpEntity = new StringEntity(reqParams, Charset.forName("UTF-8"));
        httpPost.setEntity(httpEntity);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
//            logger.info("\n--http-response--响应状态码:"+statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
//                    String result = EntityUtils.toString(entityRes, "UTF-8");
                    InputStream inputStream = entityRes.getContent();
                    ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                    byte[] buff = new byte[128]; //buff用于存放循环读取的临时数据
                    int rc = 0;
                    while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                        swapStream.write(buff, 0, rc);
                    }
                    byte[] rbyte = swapStream.toByteArray();
                    String r = Base64.encodeBase64String(rbyte);
//                    logger.info("--http-response--响应内容: \n"+r);
                    return r;
                }
            }
            return null;
        } catch (IOException e) {
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    private HttpEntity getUrlEncodedFormEntity(Map<String, Object> params) {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                    .getValue().toString());
            pairList.add(pair);
        }
        return new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8"));
    }

    private String getUrlWithParams(String url, Map<String, Object> params) {
        boolean first = true;
        StringBuilder sb = new StringBuilder(url);
        for (String key : params.keySet()) {
            char ch = '&';
            if (first == true) {
                ch = '?';
                first = false;
            }
            String value = params.get(key).toString();
            try {
                String sval = URLEncoder.encode(value, "UTF-8");
                sb.append(ch).append(key).append("=").append(sval);
            } catch (UnsupportedEncodingException e) {
            }
        }
        return sb.toString();
    }

    public void shutdown() {
        idleThread.shutdown();
    }

    // 监控有异常的链接
    private class IdleConnectionMonitorThread extends Thread {

        private final HttpClientConnectionManager connMgr;
        private volatile boolean exitFlag = false;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            this.connMgr = connMgr;
            setDaemon(true);
        }

        @Override
        public void run() {
            while (!this.exitFlag) {
                synchronized (this) {
                    try {
                        this.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 关闭失效的连接
                connMgr.closeExpiredConnections();
                // 可选的, 关闭30秒内不活动的连接
                connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
            }
        }

        public void shutdown() {
            this.exitFlag = true;
            synchronized (this) {
                notify();
            }
        }

    }


}
