package com.hm.aem.core.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hm.aem.core.service.config.SalesforceConfig;

@Component(service = SalesforceCaseService.class)
@Designate(ocd = SalesforceConfig.class)
public class SalesforceCaseImp implements SalesforceCaseService{


    @Reference
    SalesforceAuthService salesforceAuthService;

    private String apiversion;

    Gson gson = new Gson();


    @Activate
    protected void activate(SalesforceConfig config){

        this.apiversion = config.apiVersion();
    }


    @Override
    public String createCase(Map<String,String> caseData){

        try {
            String accesstoken = salesforceAuthService.getAccessToken();
            String instanceUrl = salesforceAuthService.getInstanceUrl();
            String caseApiUrl = instanceUrl + "service/data" + apiversion + "sobjects/Case";

            Map<String,String> payload = new HashMap<>();
            payload.put("Subject", caseData.get("subject"));
            payload.put("Description", caseData.get("description"));
            payload.put("SuppliedName", caseData.get("name"));
            payload.put("SuppliedEmail", caseData.get("email"));
            payload.put("Origin", "AEM WebSite");
            payload.put("Priority", "Medium");
            payload.put("Status", "New");


            String jsonPayload = gson.toJson(payload);

            URL url = new URL(caseApiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            //this is actual HTTP header send to salesforce,wittgout this 
            // salesforce wont create a case
            httpURLConnection.setRequestProperty("Authorization", "Bearer "+ accesstoken);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);

           // to send data out of java outputStream used & data come to java
           //inputStream is used, now inputstream ha data byte(eg 112,334,125 etc)
           //java cannot read this, so to convert these bytes into char
           // inputStreamReader is used.
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));

            int responseCode = httpURLConnection.getResponseCode();
            String apiResponseBody = readApiResponse(httpURLConnection, responseCode);

            JsonParser parser = new JsonParser();
            JsonObject jo = parser.parse(apiResponseBody).getAsJsonObject();
            return jo.get("id").getAsString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Salesforce case", e);
        }
    }



    private String readApiResponse(HttpURLConnection connection , int responseCode) 
    throws Exception
    
    {

        //bufferreader load chuks od data at once and provde better performance
        // and has some method like readLine().

        BufferedReader reader = new BufferedReader(new InputStreamReader(
            responseCode >= 200 && responseCode < 300
            ? connection.getInputStream()
            : connection.getErrorStream(),StandardCharsets.UTF_8));

         StringBuilder apiResponseBody = new StringBuilder();
         String line;

         while ((line = reader.readLine()) != null) {
            
            apiResponseBody.append(line);
         }

         return apiResponseBody.toString();



    }



    
}
