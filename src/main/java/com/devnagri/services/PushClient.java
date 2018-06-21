package com.devnagri.services;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * Created by Gaurav on 11.06.18.
 */

public class PushClient {

    private String response;
    private String accessToken;
    private String clientId;
    private String clientSecret;
    private String projectKey;
    private String file;
    private String fileLocation;

    public PushClient(String clientId, String accessToken, String clientSecret, String projectKey, String file, String fileLocation){
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.projectKey = projectKey;
        this.file = file;
        this.fileLocation = fileLocation;
    }

    public String pushData(){
        String urlPush = "http://dev.devnagri.co.in/api/project/push";
        String token = "Bearer "+accessToken;

        MultipartEntityBuilder entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.STRICT)
                .addTextBody("client_id", clientId)
                .addTextBody("client_secret", clientSecret)
                .addTextBody("project_key", projectKey)
                .addTextBody("file[file]", file)
                .addTextBody("file[location]", fileLocation);

        RestClient restClient = new RestClient();
        response = restClient.callService(urlPush, entity, token);
        System.out.println("Response is : "+ response);
        return response;
    }

}
