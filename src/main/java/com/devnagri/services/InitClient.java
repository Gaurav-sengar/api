package com.devnagri.services;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gaurav on 11.06.18.
 */

public class InitClient {

    public String accessToken;
    public String clientId;
    public String clientSecret;
    public String projectKey;
    public String file;
    public String fileLocation;
    private static String response;
    private static String requestType;


    public String getResponseRequest(String requestType, String accessToken, String clientId, String clientSecret, String projectKey, String file , String fileLocation, String project){
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.projectKey = projectKey;
        this.file = file;
        this.fileLocation = fileLocation;
        this.requestType = requestType;

        if ("init".equals(requestType)) {
            GenerateTokenClient generateTokenClient = new GenerateTokenClient(clientId, clientSecret, projectKey);
            response = generateTokenClient.getGeneratedToken();
            if(null!=response && !response.equalsIgnoreCase("")){
                accessToken = generateTokenClient.getToken();
                this.accessToken = accessToken;
                init(project);
            }

        } else if ("status".equals(requestType)) {
            StatusClient statusClient = new StatusClient(accessToken, clientId, clientSecret, projectKey);
            response = statusClient.getStatus();
        } else if ("pull".equals(requestType)) {
            PullClient pullClient = new PullClient(accessToken, clientId, clientSecret, projectKey);
            response = pullClient.pullData();
        } else if ("push".equals(requestType)) {
            PushClient pushClient = new PushClient(accessToken, clientId, clientSecret, projectKey, file, fileLocation);
            response = pushClient.pushData();
        } else if ("sync".equals(requestType)) {
            SyncClient syncClient = new SyncClient(accessToken, clientId, clientSecret, projectKey, file, fileLocation);
            response = syncClient.syncData();
        }

        return response;
    }

    public void init(String project){

        DevnagriConfiguration configuration = new DevnagriConfiguration(project);
        Map<String, String> cmap = getConfigMap();
        configuration.generateConfig(cmap, project);
        //configuration.loadPhraseAppConfig();
        System.out.println("Main Class init method called");

    }

    /*public void testDumpWriter() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[] { "ONE_HAND", "ONE_EYE" });

        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter(project.getBasePath() + "/.devnagri.yml");
        yaml.dump(data, writer);
    }*/

    private Map<String, String> getConfigMap() {
        Map<String, String> base = new HashMap<String, String>();

        base.put("AccessToken", accessToken);
        base.put("ClientID", clientId);
        base.put("ClientSecret", clientSecret);
        base.put("ProjectKey", projectKey);
        base.put("RootFolder", "values");
        base.put("Extension", ".xml");
        base.put("SourceLanguage", "en");
        base.put("TargetLanguages", "br");

        return base;
    }

   /* private static Map<String, Object> getPushConfig() {
        Map<String, Object> push = new HashMap<String, Object>();

        push.put("AccessToken", "dkjhkdshdkshdafksjdajkhjhfa");
        push.put("ClientID", "adhkdajhlkjdah");
        push.put("ClientSecret", "sdajhkjashkl");
        push.put("ProjectKey", "kdsgkjhagdskhjdags");
        push.put("RootFolder", "ajhjdashkljdas");
        push.put("Extension", "dsajhkjhkjsdhakjsh");
        push.put("SourceLanguage", "kjdashkjdahlkjdahs");
        push.put("TargetLanguages", "ahgfdasjkgdksfahgkdjhfsg");

        // pushFile.put("file", defaultLocalePath);
        // push.put("sources", new Map[]{pushFile});

        return push;
    }*/

}
