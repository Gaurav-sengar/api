package com.devnagri.services;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Created by Gaurav on 11.06.18.
 */

public class RestClient {

    public String callService(String myUrl, MultipartEntityBuilder myRequest, String token)
    {

        /* TARGET URL AND JSON */
        String url = myUrl;

        /* HTTPCLIENT AND HTTPPOST OOBJECT */
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);


        /* ADD HEADER INFO */
        if(!token.equalsIgnoreCase("")){

            request.addHeader("authorization", token);

        }

        request.addHeader(/*"content-type"*/"Accept", "application/json");
        //request.addHeader("content-type", /*"application/x-www-form-urlencoded"*/"form-data");
        /*Map<String, String> map = new HashMap<>();
        map.put("client_id", "1");
        map.put("client_secret", "uA9nGZeiOTZ8KbFCjmCke21YLdqkDqF3ecwkHXvQ");
        map.put("project_key", "1bbbb84f24818351917a67b23e5e48e1");
        request.setEntity(map);*/

        /* PROXY CONFIG */
      /*  HttpHost target = new HttpHost("proxy", 8080, "http");
        RequestConfig config = RequestConfig.custom().setProxy(target).build();
        request.setConfig(config);*/

        /* JSON AS STRINGENTITY */

        /*MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        *//* example for setting a HttpMultipartMode *//*
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        *//* example for adding an image part *//*
        FileBody fileBody = new FileBody(new File(image)); //image should be a String
        builder.addPart("my_file", fileBody);*/
        /*StringEntity input = null;
        try {
            input = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        HttpEntity multiPartEntity = myRequest.build();
        request.setEntity(multiPartEntity);

        /* SEND AND RETRIEVE RESPONSE */
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* RESPONSE AS JSON STRING */
        String result = null;
        try {
            result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

   /* public static void main(String[] args) {
        com.devnagri.services.RestClient restClient = new com.devnagri.services.RestClient();
        String response = restClient.callService();
        System.out.println(response);
    }*/

}
