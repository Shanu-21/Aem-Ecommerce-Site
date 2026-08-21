package com.hm.aem.core.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Component(service = Servlet.class)

@SlingServletPaths(value = {"/bin/call"})

public class ThirdPartyCall  extends SlingAllMethodsServlet{

private static final Logger log = LoggerFactory.getLogger(ThirdPartyCall.class);


@Override
protected void doGet(SlingHttpServletRequest request , SlingHttpServletResponse response) throws ServletException, IOException

{

    try {


        String id = request.getParameter("id");
        String api_url = "https://api.restful-api.dev/objects/" + id;

        URL url = new URL(api_url);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
    


        if (connection.getResponseCode() != 200) {
            log.info("Connection Failed", connection.getResponseCode());
        }
        

        Gson gson = new Gson();
        InputStreamReader in = new InputStreamReader(connection.getInputStream());
        Map<String , Object> Result = gson.fromJson(in, Map.class);
        String data = gson.toJson(Result);

        if (data != null) {

            response.setContentType("application/json");
            response.getWriter().println("API Call is Successful = " + connection.getResponseCode());            
            response.getWriter().println(data);

        }

        

        
    } catch (Exception e) {
        log.info("Api called Failed" ,  e);
    }
 



}
}
