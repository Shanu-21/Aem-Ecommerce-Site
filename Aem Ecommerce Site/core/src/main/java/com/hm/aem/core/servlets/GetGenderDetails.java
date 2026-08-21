package com.hm.aem.core.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.net.HttpURLConnection;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hm.aem.core.service.config.GenderConfigService;

@Component(service = Servlet.class , immediate = true)
@SlingServletPaths(value = "/bin/genderdetails")
public class GetGenderDetails extends SlingAllMethodsServlet{

    @Reference
    GenderConfigService genderConfigService;


    private static final Logger log = LoggerFactory.getLogger(GetGenderDetails.class);

    @Override
    public void doGet(SlingHttpServletRequest request , SlingHttpServletResponse response)
    throws IOException,ServletException
    
    {
        try {

            String status = "Servlet Running";
            
            String Apiurl = genderConfigService.getGenderAPI();

            if (Apiurl != null) {

                URL url = new URL(Apiurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            if (httpURLConnection.getResponseCode()!=200) {

            log.info("Connection Failed", httpURLConnection.getResponseCode());
                
            }
            else {
                log.info("Connect successfully", httpURLConnection.getResponseCode());

                Gson gson = new Gson();
                InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream());
                Map<String , Object> gender = gson.fromJson(reader, Map.class);

                log.info("Connect successfully", httpURLConnection.getResponseCode());
                
        

                String genderData = gson.toJson(gender);

                if (genderData != null) {

                    response.setContentType("application/json");

                    response.getWriter().println(status);
                    response.getWriter().println(genderData);
                    response.getWriter().println("Connction Succesfull = " +  httpURLConnection.getResponseCode());
                    
                }

                
            }

                
            }

            

        } catch (Exception e) {
            // TODO: handle exception

            log.info("Api called Failed" ,  e);
        }




    }
    
}
