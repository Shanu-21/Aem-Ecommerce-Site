package com.hm.aem.core.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hm.aem.core.service.config.SalesforceConfig;


@Component(service = SalesforceAuthService.class, immediate = true)
@Designate(ocd = SalesforceConfig.class)
public class SalesforceAuthImpl implements SalesforceAuthService{


    private String tokenUrl;
    private String clientId;
    private String clientSecret;

    private String accessToken;
    private String instanceUrl;
    private long tokenExpiryTime;
    

    @Activate
    protected void activate(SalesforceConfig salesforceConfig){

        this.tokenUrl = salesforceConfig.tokenUrl();
        this.clientId = salesforceConfig.clientId();
        this.clientSecret = salesforceConfig.clientSecret();
        }


    @Override
    public synchronized String getAccessToken(){

        try {

            //checking acckentoken exit and not expired
            // if fail will genarta a new by calling api
            if (accessToken != null && System.currentTimeMillis() < tokenExpiryTime) {
                return accessToken;
                
            }

            //A reuest body to send request to get acces token from salesforce  
            String reuestBody = "grant_type=client_credentials" +
            "&client_id=" + URLEncoder.encode(clientId, "UTF-8") +
            "&client_secret=" + URLEncoder.encode(clientSecret, "UTF-8");

            URL url = new URL(tokenUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            //type of reuest body we are sending 
            connection.setRequestProperty("Content-type", "aplication/x-www-form-urlencoded");
            //tell java that we are sending a data in reuest body via 
            // output stream other wise the outputstream code will fail,
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(reuestBody.getBytes(StandardCharsets.UTF_8));

            int reponseCode = connection.getResponseCode();
            String reponseBody = readResponse(connection, reponseCode);
            
            //ObjectMapper mapper = new ObjectMapper(); - this will use when we have more than 4-5
            // response from api. + DTO Class\\

            if (reponseCode >= 200 && reponseCode < 300) {
                
            JsonParser parser = new JsonParser();
            JsonObject jo = parser.parse(reponseBody).getAsJsonObject();
            this.accessToken = jo.get("access_token").getAsString();
            this.instanceUrl = jo.get("instance_url").getAsString();

            //her we are savin the token expire time time-50min is valid time
            this.tokenExpiryTime = System.currentTimeMillis() + (50 * 60 * 1000);
            return this.accessToken;

        }
        
        throw new RuntimeException("Salesforce token API Failed:" + reponseBody);         

        } catch (Exception e) {
            throw new RuntimeException("Error while getting Saleforce Access Token", e);

        }


    }

    @Override
    public  String getInstanceUrl(){

        if (instanceUrl == null || System.currentTimeMillis() >= tokenExpiryTime) {
            getAccessToken();
        }

        return instanceUrl;
    }


    private String readResponse(HttpURLConnection connection, int responseCode) throws Exception{

        //we can parse josn data directly from the inpustream using jackson or gson
        // but we are using this readresponse method because it provide many advantage
        // like logging,debugging and reusin thi method for other api as well.

         BufferedReader reader = new BufferedReader((new InputStreamReader(
            responseCode >= 200 && responseCode < 300
            ? connection.getInputStream()
            : connection.getErrorStream(),
            StandardCharsets.UTF_8)));

            // here will recevie the json response from the api and the we will read each line and 
            //add to string and return as string
            StringBuilder response = new StringBuilder(); //use to build complete response
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

           return response.toString();

    }



}
