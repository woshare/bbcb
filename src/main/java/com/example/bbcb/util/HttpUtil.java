package com.example.bbcb.util;

import com.example.bbcb.exception.BusinessExceptionStatusEnum;
import com.example.bbcb.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Slf4j
public class HttpUtil {
    /**
     *http/https RPC请求
     * @param url
     * @param httpMethod
     * @param contentType :1,"application/x-www-form-urlencoded"
     * @param httpParams
     * @return
     */
    public String HttpClient(String url, HttpMethod httpMethod,String contentType, MultiValueMap<String,String> httpParams){
        try {
            String tmpUrl=url.toLowerCase();
            RestTemplate restTemplate=null;
            if(tmpUrl.indexOf("https://") == 0){
                //忽略证书。 否则报错 sun.security.validator.ValidatorException: PKIX path building failed
                restTemplate = restTemplate();
            }else{
                restTemplate = new RestTemplate();
            }
            ResponseEntity<String> responseEntity = null;
            if (httpMethod == HttpMethod.GET) {
                responseEntity = restTemplate.getForEntity(url, String.class);
            } else if (httpMethod == HttpMethod.POST) {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Content-Type", contentType);
                org.springframework.http.HttpEntity<MultiValueMap<String, String>> httpEntity = new org.springframework.http.HttpEntity<>(httpParams, httpHeaders);
                responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
            }
            return responseEntity.getBody();
        }catch(Exception e){
            throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.HttpRequstException, e.getMessage());
        }
    }
    private static RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
