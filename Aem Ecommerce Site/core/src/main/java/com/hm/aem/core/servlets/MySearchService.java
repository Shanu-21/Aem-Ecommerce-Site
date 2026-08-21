package com.hm.aem.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.Servlet;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;


@Component(service = Servlet.class)
@SlingServletPaths( value ={"/apps/SearchQuery"} )

public class MySearchService extends SlingSafeMethodsServlet{

    public static final Logger log = LoggerFactory.getLogger(MySearchService.class);

    @Reference
    QueryBuilder queryBuilder;

      

    public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException{
        
        
        Map<String,String> pre = new HashMap<String,String>();
        pre.put("type", "cq:page");
        pre.put("path", "/content/hm");
        //pre.put("fulltext", "women");
        pre.put("p.limit", "5");

        ResourceResolver resolver = req.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);

    Query query =  queryBuilder.createQuery(PredicateGroup.create(pre),session);  
       SearchResult result = query.getResult();


       JSONArray reJsonArray = new JSONArray();

       List<Hit> hits = result.getHits();
       for (Hit h: hits){
       JSONObject jsonObject = new JSONObject();
        try {
                jsonObject.put("title", h.getTitle());
                jsonObject.put("Path", h.getPath());
                reJsonArray.put(jsonObject);

            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        
        }
        res.setContentType("application/json");
        res.getWriter().write(reJsonArray.toString() + "\n");

       }
              

        
    }


 
    
}
