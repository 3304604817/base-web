package com.base.common.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static com.mysql.cj.conf.PropertyKey.socketTimeout;

/**
 * @author gaoyang
 * RestTemplate 配置
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 最大连接数
     */
    private int maxTotal = 100;
    /**
     * 并发数
     */
    private int defaultMaxPerRoute = 20;
    /**
     * 创建连接的最长时间
     */
    private int connectTimeout = 1000;
    /**
     * 从连接池中获取到连接的最长时间
     */
    private int connectionRequestTimeout = 500;
    /**
     * 数据传输的最长时间
     */
    private int socketTimeout = 10000;
    /**
     * 提交请求前测试连接是否可用
     */
    private boolean staleConnectionCheckEnabled = true;
    /**
     * 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
     */
    private int validateAfterInactivity = 3000000;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        /**
         * maxTotal：最大连接数
         * defaultMaxPerRoute：单个路由最大连接数
         * validateAfterInactivity：最大空间时间
         * socketTimeout：服务器返回数据(response)的时间，超过抛出read timeout
         * connectTimeout：连接上服务器(握手成功)的时间，超出抛出connect timeout
         * staleConnectionCheckEnabled：提交前检测是否可用
         * connectionRequestTimeout：从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
         */
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connectionManager.setValidateAfterInactivity(validateAfterInactivity);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
