package com.devnagri.services;


import java.io.IOException;

/**
 * Created by Gaurav on 11.06.18.
 */

public class MainClass {

    private static String response="";
    private static String requestType;
    public static String accessToken;
    public static String clientId;
    public static String clientSecret;
    public static String projectKey;
    public static String file;
    public static String fileLocation;
    public static String project;


    public String setConfigData(String requestType, String accessToken, String clientId, String clientSecret, String projectKey, String file , String fileLocation, String project){
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.projectKey = projectKey;
        this.file = file;
        this.fileLocation = fileLocation;
        this.requestType = requestType;
        this.project = project;
        if(null!= requestType && !requestType.equalsIgnoreCase("")){
            InitClient initClient = new InitClient();
            response = initClient.getResponseRequest(requestType, accessToken, clientId, clientSecret, projectKey, file, fileLocation, project);
        }else{
            //Notifications.Bus.notify(new com.intellij.notification.Notification("devnagri", "Error", "Please set config first", NotificationType.ERROR));
        }
        return response;
    }

    public static void main(String[] args) throws IOException {

       // InitClient initClient = new InitClient();
       // response = initClient.getResponseRequest("init","eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjcxM2Q0NjZkMzYxNGM2YTExYjI4ZjA3ODhhOTUxMTViMjE4YzY4YjFhNjJjYjU2MzU5ODJlMmMzMTVmNzg0MmZhNDc4MDZhNjI3Y2IxOTdlIn0.eyJhdWQiOiIxIiwianRpIjoiNzEzZDQ2NmQzNjE0YzZhMTFiMjhmMDc4OGE5NTExNWIyMThjNjhiMWE2MmNiNTYzNTk4MmUyYzMxNWY3ODQyZmE0NzgwNmE2MjdjYjE5N2UiLCJpYXQiOjE1MjkzOTI5MzMsIm5iZiI6MTUyOTM5MjkzMywiZXhwIjoxNTYwOTI4OTMzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.LU7l0T7YwXtzRWeJ2R-LHpK1JNOfZW0O7QOS0G63nuUr3x-Q4oZUsShGnX8XALuvV-xgLnVJIy2oYWcNz-MQUM55xrkP3edmKSPvI0bm5GbL3-U2SsYNs0oRPtV5sJNU4twW9ymBf8jyjkRPjJ5hmUIV5a2u0TPZTt3dAVMlJs7Pe17BRWte_VVA1pBI6PcLjLPAToHrUBgx4j4JeKNDtKhrGjByZQvym_eNxaMnU43gE9_NZ4MeC9q3gBOuwoMs_O7BNxKXfTmdlhQ-RzJ254xRL78UDTgAbt5KDfXTahuLhY55pjrSfLTexu1IfQf7OL0cN048R0hOst-oPDVx9quIG0qVzRthkPLvcCnbJsweuazxIiMqQchRL5ewuVdEu8BQNMqqJ8VUUajresemE4elLhGpzfRhTPeEhG04YnglwpP9I6PDOerESTHBRQZzC8A7mInVoDPw4gCeaP97aOB5XwBe7VejPHaMMDVf-g_-wvCxJTS67fZJFSW1ETxMtWJTlO6vEmvvdw8WcX3e1VldaeeQznaHORxUtVnD_fNmP-20aU4qNF8PLjBvXn3IKLEsjxo0zk2lXQecKTMPkBTLc_ZhPYt9Ij_1b-WS-ptHEXe5MfrWC8KAivrd_YQth3XH3BFGyXqxVjIaEhMGhQQZWLNOmN05PqX4nqs7M8o","3","vZ1pwI6CzwpBhz346KrdUrlvwA1evi3NghKsx1LK","dac5735828cd4a70819147fb36d24411","","",System.getProperty("user-dir"));
        //DevnagriConfiguration d = new DevnagriConfiguration(System.getProperty("user-dir"));
        //System.out.println(System.getProperty("user-dir"));
        /*d.getAccessToken();
        System.out.println(d.getAccessToken());*/
       // System.out.println("Response: " + d.getAccessToken()+" ;;;; "+d.getClientId()+" ;;;; "+d.getClientSecret()+" ;;;; "+d.getProjectKey());

       /* byte[] hash = d.getSHA256("/home/fourtek/IdeaProjects/sampletwo/SyncClient.java");
        System.out.println(hash);*/
    }

    public static String getResponse(){
        return response;
    }

}
