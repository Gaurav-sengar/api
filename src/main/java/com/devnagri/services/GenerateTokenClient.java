package com.devnagri.services;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Gaurav on 11.06.18.
 */

public class GenerateTokenClient {

    private String response;
    public String token = "";
    public String clientId;
    public String clientSecret;
    public String projectKey;

    public GenerateTokenClient(String clientId, String clientSecret, String projectKey){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.projectKey = projectKey;
    }

    public String getGeneratedToken(){
        String urlGenerateToken = "http://dev.devnagri.co.in/api/key/validations";

       /* MultipartEntityBuilder entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.STRICT)
                .addTextBody("client_id", "3")
                .addTextBody("client_secret", "vZ1pwI6CzwpBhz346KrdUrlvwA1evi3NghKsx1LK")
                .addTextBody("project_key", "dac5735828cd4a70819147fb36d24411");*/

        MultipartEntityBuilder entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.STRICT)
                .addTextBody("client_id", clientId)
                .addTextBody("client_secret", clientSecret)
                .addTextBody("project_key", projectKey);

        RestClient restClient = new RestClient();
        response = restClient.callService(urlGenerateToken, entity, token);
        if(null!=response && !response.equalsIgnoreCase("")){
            JSONObject jsonObject = new JSONObject(response);

            String token_type = jsonObject.getString("token_type");
            String access_token = jsonObject.getString("access_token");

            token = access_token;
            //System.out.println("Response is : "+ response);
            //System.out.println("Token : "+ token);

            final String fileName = "devnagri.yml";
            ArrayList<String> key = new ArrayList<String>();
            ArrayList<String> value = new ArrayList<String>();
            Yaml yaml = new Yaml();

            try {
                InputStream ios = new FileInputStream(new File(fileName));

                // Parse the YAML file and return the output as a series of Maps and Lists
                Map< String, Object> result = (Map< String, Object>) yaml.load(ios);
                for (Object name : result.keySet()) {
                    key.add(name.toString());
                    value.add(result.get(name).toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //System.out.println(key + " " + value);

        return response;
    }

  /*  public static void main(String args[]){
        String urlGenerateToken = "http://dev.devnagri.co.in/api/key/validations";
        String token = "";
        String response = "";
        *//*MultipartEntityBuilder entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.STRICT)
                .addTextBody("client_id", "3")
                .addTextBody("client_secret", "vZ1pwI6CzwpBhz346KrdUrlvwA1evi3NghKsx1LK")
                .addTextBody("project_key", "dac5735828cd4a70819147fb36d24411");*//*

        MultipartEntityBuilder entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.STRICT)
                .addTextBody("client_id", clientId)
                .addTextBody("client_secret", clientSecret)
                .addTextBody("project_key", projectKey);

        RestClient restClient = new RestClient();
        response = restClient.callService(urlGenerateToken, entity, token);
        System.out.println(response);
    }
*/
    /*public void testDumpWriter() throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("ClientID", "");
        data.put("ClientSecret", "");
        data.put("ProjectKey", "");
        data.put("RootFolder", "");
        data.put("Extension", "");
        data.put("SourceLanguage", "");
        data.put("TargetLanguages", "");

        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter("/path/to/devnagri.yml");
        yaml.dump(data, writer);
    }*/


    public String getToken(){
        return token;
    }

}
