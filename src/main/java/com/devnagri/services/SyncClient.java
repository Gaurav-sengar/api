package com.devnagri.services;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * Created by Gaurav on 11.06.18.
 */

public class SyncClient {

    private String response;
    private String accessToken;
    private String clientId;
    private String clientSecret;
    private String projectKey;
    private String file;
    private String fileLocation;

    public SyncClient(String accessToken, String clientId, String clientSecret, String projectKey, String file, String fileLocation){
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.projectKey = projectKey;
        this.file = file;
        this.fileLocation = fileLocation;
    }

    public String syncData(){

        String urlSync = "http://dev.devnagri.co.in/api/project/sync";
        String token = "Bearer "+accessToken;

        MultipartEntityBuilder entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.STRICT)
                .addTextBody("client_id", clientId)
                .addTextBody("client_secret", clientSecret)
                .addTextBody("project_key", projectKey)
                .addTextBody("file[0]", file)
                .addTextBody("file[location]", fileLocation);

        RestClient restClient = new RestClient();
        response = restClient.callService(urlSync, entity, token);
        System.out.println("Response is : "+ response);

        return response;
    }
}
