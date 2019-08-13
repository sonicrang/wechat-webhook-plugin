package io.jenkins.plugins.wechat;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import io.jenkins.plugins.wechat.model.weChatData;

import com.google.gson.Gson;

public class WeChatMessage {
    private HttpPost httpPost;// 用于提交登陆数据

    private static Gson gson = new Gson();

    /**
     * 创建微信发送请求post数据，markdown格式
     * 
     * @param content 内容
     * @return String
     */
    protected String createpostdata(String content) {
        weChatData wcd = new weChatData();
        wcd.setMarkdown(content);

        return gson.toJson(wcd);
    }

    /**
     * 创建微信发送请求post实体
     * 
     * @param url  微信消息发送请求地址
     * @param data post数据
     * @return String
     * @throws IOException 异常
     */
    public String post(String url, String data) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(data, "utf-8"));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        LoggerFactory.getLogger(getClass()).info("call [{}], param:{}, resp:{}", url, data, resp);
        return resp;
    }

}