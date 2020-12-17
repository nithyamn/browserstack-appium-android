package com.browserstack;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.SessionId;

import java.net.URI;

class SessionDetails {
    String username, accesskey;

    public SessionDetails(String username, String accesskey){
        this.username = username;
        this.accesskey = accesskey;
    }
    public void fetchData(SessionId sessionId) throws Exception{
        URI uri = new URI("https://"+username+":"+accesskey+"@api.browserstack.com/app-automate/sessions/"+sessionId+".json"); //App Automate
        //System.out.println(uri.resolve(uri));
        HttpGet getRequest = new HttpGet(uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse httpresponse = httpclient.execute(getRequest);

        String jsonResponseData = EntityUtils.toString(httpresponse.getEntity());
        String trimResposneData = jsonResponseData.substring(22, jsonResponseData.length()-1);

        //System.out.println(trimResposneData);
        JSONParser parser = new JSONParser();
        JSONObject bsSessionData = (JSONObject) parser.parse(trimResposneData);
        //System.out.println(bsSessionData);

        //System.out.println(bsSessionData.get("public_url"));
        String printData = "Name: "+bsSessionData.get("name")+"\nBuild: "+bsSessionData.get("build_name")+"\nProject: "+bsSessionData.get("project_name")+"\nStatus: "+bsSessionData.get("status")+"\nReason: "+bsSessionData.get("reason")+"\nPublic Session URL: "+bsSessionData.get("public_url");

        System.out.println(printData);
    }

}
