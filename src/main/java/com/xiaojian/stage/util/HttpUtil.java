package com.xiaojian.stage.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.print.URIException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;


/**
 * Created by xiaojian on 2017/9/1.
 */
public class HttpUtil {

    private static Logger logger = Logger.getLogger(HttpUtil.class);
    private static final String READ_FAILED = "{\"result\":\"read failed!\"}";
    public static String getData(String data,String urlName,String type){
        String url = getUrl(urlName);
        if (url==null){
            return READ_FAILED;
        }
        String responseData = getRequest(data,url,type);
        logger.info("response---------------->"+responseData);
        return responseData;
    }

    //发送请求
    public static String getRequest( String data,String url,String type){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse=null;
        try {
            if (type.equals("GET")) {
                HttpGet httpGet = new HttpGet(getURI(url, data));
                logger.info("访问地址---------------->" + httpGet.getURI());
                 httpResponse = httpClient.execute(httpGet);
            } else if (type.equals("POST")) {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new StringEntity(data, "UTF-8"));
                logger.info("访问地址---------------->" + httpPost.getURI());
            }
        }catch (IOException e){
                e.printStackTrace();
            }
        return getResponse(httpResponse);
    }
    //获得返回数据
    public static String getResponse(HttpResponse response){
        String responseData="{\"result\":\"访问失败!\"}";
        if (response!=null){
            int statusCode = response.getStatusLine().getStatusCode();
            switch (statusCode){
                case 200:
                    try {
                        responseData=EntityUtils.toString(response.getEntity(),"UTF-8");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                break;
                case 404:
                    responseData="{\"result\":\"404!请求的网页不存在!\"}";
                break;
                case 500:
                    responseData="{\"result\":\"500!服务不可用!\"}";
                break;
            }
        }
        return responseData;
    }

    //生成URI
    public static URI getURI(String url,String data){
        URI uri = null;
        try {
            uri = new URIBuilder(url)
                    .setParameter("json",data)
                    .build();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
        return uri;
    }

//读取url
    public static String getUrl(String urlName){
        Configuration configuration = null;
        try {
             configuration = new PropertiesConfiguration("url.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return configuration.getString(urlName);
    }

}
