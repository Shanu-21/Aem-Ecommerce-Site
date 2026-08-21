package com.hm.aem.core.service.config;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hm.aem.core.dtobeans.HmProductDto;

@Component(service = HmProductService.class)
public class HmProductImp implements HmProductService{

private static final Logger log = LoggerFactory.getLogger(HmProductImp.class);

private String apiUrl = "https://dummyjson.com/products/";

@Override
public HmProductDto getProductDetails(String productId)
{
 
    try {

    if (StringUtils.isNotBlank(productId)) {

        URL url = new URL(apiUrl + productId);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if(connection.getResponseCode() != 200){

        log.info("Connection Failed", connection.getResponseCode()); 
         return null;
         }

        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        HmProductDto reponseDto = gson.fromJson(reader, HmProductDto.class);

        if (reponseDto != null && productId.equals(reponseDto.getId())) {

                return reponseDto;
                
            }
            
        }
        
        
    } catch (Exception e) {
        // TODO: handle exception
            log.error("Error while fetching product", e);

    }
    
return null;



}
    
}
