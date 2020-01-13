package com.jr.juhe.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JhHttpUtil {
    private static int SOCKET_TIMEOUT = 180000;
    private static int CONNECT_TIMEOUT = 180000;
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    public static String sendPost(String url, String key, String type, File file, String userid, String orderid)
            throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse httpResonse = null;
        try {
            RequestConfig config = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT).setAuthenticationEnabled(false).build();

            HttpPost post = new HttpPost(url);
            post.setProtocolVersion(HttpVersion.HTTP_1_1);
            post.setConfig(config);

            FileBody bin = new FileBody(file);

            StringBody keyBody = new StringBody(key, ContentType.TEXT_PLAIN);
            StringBody typeBody = new StringBody(type, ContentType.TEXT_PLAIN);
            StringBody useridBody = new StringBody(userid, ContentType.TEXT_PLAIN);
            StringBody orderidBody = new StringBody(orderid, ContentType.TEXT_PLAIN);

            HttpEntity entity = MultipartEntityBuilder.create().addPart("pic", bin).addPart("key", keyBody)
                    .addPart("cardType", typeBody).addPart("userid", useridBody).addPart("orderid", orderidBody)
                    .build();

            post.setEntity(entity);
            httpResonse = client.execute(post);
            if (httpResonse.getStatusLine().getStatusCode() == 200) {
                return "";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpResonse.close();
            client.close();
        }
        httpResonse.close();
        client.close();

        return null;
    }

    public static String sendNet(String strUrl, Map params, String method)
            throws Exception {
        return "";
    }
    public static String urlencode(Map<String, Object> data)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
        }
        return sb.toString();
    }

    public static String net(String url, Map<String,Object> map, String post) {
        return "";
    }
}
