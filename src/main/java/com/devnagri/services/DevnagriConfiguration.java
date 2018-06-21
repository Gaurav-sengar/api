package com.devnagri.services;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Gaurav on 11.06.18.
 */
public class DevnagriConfiguration {
    private String currentConfig;
    private String project;

    /*public DevnagriConfiguration(){
        loadPhraseAppConfig(String project);
    }*/

    public DevnagriConfiguration(String project){
        this.project = project;
        loadPhraseAppConfig(project);
    }

    public void setConfig(String s, String project) {
        this.project = project;
        try {
            File configFile = new File(project + "/.devnagri.yml");
            FileUtils.writeStringToFile(configFile, s);
            //LocalFileSystem.getInstance().refreshIoFiles(Collections.singletonList(configFile));
            currentConfig = s;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRoot() throws IOException, URISyntaxException {
        URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
        File f = new File(u.toURI());
        System.out.println(f.getParent());
        return f.getParent();
    }

    public void generateConfig(Map config, String project) {
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(config, writer);
        setConfig(writer.toString(), project);

    }

    public void loadPhraseAppConfig(String project) {
        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(project + "/.devnagri.yml"), "UTF-8");
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine() + NL);
                //new Notification("devnagri", "Error", "read yaml file", NotificationType.ERROR);
                System.out.println(text);
            }
        } catch (FileNotFoundException e) {
            // Do nothing.
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        this.currentConfig = text.toString();
        //System.out.println(text.toString());
    }

    public String getProjectId() {
        String projectId = null;
        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            //Map root = (Map) configYml.get("devnagri");
            if (!configYml.isEmpty()) {
                projectId = (String) configYml.get("ProjectKey");
            }
        }

        return projectId;
    }

    /*public String getAccessToken() {
        String accessToken = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            Map root = (Map) configYml.get("devnagri");
            if (root != null) {
                accessToken = (String) root.get("AccessToken");
            }
        }

        return accessToken;
    }*/

    public String getAccessToken() {
        String accessToken = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                accessToken = (String) configYml.get("AccessToken");
            }
        }

        return accessToken;
    }

    public String getClientId() {
        String clientId = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                clientId = (String) configYml.get("ClientID");
            }
        }

        return clientId;
    }

    public String getClientSecret() {
        String clientSecret = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                clientSecret = (String) configYml.get("ClientSecret");
            }
        }

        return clientSecret;
    }

    public String getProjectKey() {
        String projectKey = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                projectKey = (String) configYml.get("ProjectKey");
            }
        }

        return projectKey;
    }

    public String getRootFolder() {
        String rootFolder = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                rootFolder = (String) configYml.get("RootFolder");
            }
        }

        return rootFolder;
    }

    public String getExtension() {
        String extension = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                extension = (String) configYml.get("Extension");
            }
        }

        return extension;
    }

    public String getSourceLanguage() {
        String sourceLanguage = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                sourceLanguage = (String) configYml.get("SourceLanguage");
            }
        }

        return sourceLanguage;
    }

    public List<String> getTargetLanguage() {
        List<String> targetLanguage = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            if (!configYml.isEmpty()) {
                for(int i = 0; i<((List<String>)configYml.get("TargetLanguages")).size(); i++){
                    targetLanguage.add(((List<String>)configYml.get("TargetLanguages")).get(i));/* = (String) configYml.get("TargetLanguages");*/
                }

            }
        }

        return targetLanguage;
    }

   /* public String getLocaleId() {
        String localeId = null;

        if (configExists()) {
            Yaml yaml = new Yaml();
            Map configYml = (Map) yaml.load(currentConfig);
            Map root = (Map) configYml.get("devnagri");
            if (root != null) {
                Map push = (Map) root.get("push");
                if (push != null) {
                    List<Map> sources = (List<Map>) push.get("sources");
                    Map source = sources.get(0);
                    Map params = (Map) source.get("params");
                    if (params != null){
                        localeId = (String) params.get("locale_id");
                    }
                }
            }
        }

        return localeId;
    }*/

    public boolean configExists() {
        System.out.println("current config value: "+ currentConfig);
        if(currentConfig!=null && !currentConfig.equalsIgnoreCase("")){
            return true;
        }else{
            return false;
        }
    }

    //Getting SHA256 of a file in java....
    public byte[] getSHA256(String path) throws IOException {

        byte[] buffer= new byte[8192];
        int count;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        while ((count = bis.read(buffer)) > 0) {
            digest.update(buffer, 0, count);
        }
        bis.close();

        byte[] hash = digest.digest();
        System.out.println(new BASE64Encoder().encode(hash));
        return hash;
    }
}
