package com.hm.aem.core.models;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Weatherdata {

    public static final Logger log = LoggerFactory.getLogger(Weatherdata.class);

   @ValueMapValue
    private String title;    

    @ValueMapValue
    private String cityname;

    @ValueMapValue
    @Default(values = "1")
    private String userID;
    
    public String getUserID() {
        return userID;
    }

    private String id;
    private String name;
    private String color;
    private String capacity;
    


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getColor() {
        return color;
    }


    public String getCapacity() {
        return capacity;
    }


    public String getTitle() {
        return title;
    }
    

    public String getCityname() {
        return cityname;
    }


    @PostConstruct
    public void init(){

        if (userID != null) {

        String apiURL = "https://api.restful-api.dev/objects/" + userID;
        fetchdata(apiURL);
    }

    
}

    private void fetchdata( String apiURL){

        try {
            
           URL url = new URL(apiURL);
           HttpURLConnection conn = (HttpURLConnection)url.openConnection();
           conn.setRequestMethod("GET");
           conn.setRequestProperty("Accept", "application/json");

           if (conn.getResponseCode() != 200) {
             throw new RuntimeException("Failed : HTTP Error Code " + conn.getResponseCode());
           }

           Gson gson = new Gson();
           InputStreamReader in = new InputStreamReader(conn.getInputStream());
           ExternalData response = gson.fromJson(in, ExternalData.class);

           if (response.getId() != null && response.getName() != null) 
           {
            if (this.userID.equals(response.getId()))
            {
           
           this.id = response.getId();
           this.name = response.getName();


           if (response.getData() != null) {

            this.color = response.getData().getColor();
           this.capacity = response.getData().getCapacity();

           }

        }

        }
           


        } catch (Exception e) {

            log.info("Api called Failed" + e);
         }
    }

    private static class ExternalData{
           
        
        private String id;
        private String name;
        private Data data;

        public String getId() {
            return id;
        }
        

        public String getName() {
            return name;
        }


        

        public Data getData() {
            return data;
        }

        



        public static class Data{

            private String color;
            private String capacity;

            public String getColor() {
                return color;
            }
           

            public String getCapacity() {
                return capacity;
            }
            
        }

    }
}



