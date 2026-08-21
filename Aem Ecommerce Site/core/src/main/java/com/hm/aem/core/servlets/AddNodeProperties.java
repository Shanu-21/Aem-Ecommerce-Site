package com.hm.aem.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class , immediate = true )
@SlingServletPaths( value = "/apps/addNodeProperties")


public class AddNodeProperties  extends SlingAllMethodsServlet{

private static final Logger log = LoggerFactory.getLogger(AddNodeProperties.class);


@Override
protected void doGet(SlingHttpServletRequest servletRequest , SlingHttpServletResponse servletResponse)
    throws IOException, ServletException
    
    {
          String Path = "/content/we-retail/us/jcr:content";
            String status = "Running Servlet";


        try {
       

            ResourceResolver resolver = servletRequest.getResourceResolver();

          //  Resource resource = resolver.getResource(Path);
           // Node ne = resource.adaptTo(Node.class);
            

            Session session = resolver.adaptTo(Session.class);
            Node node = session.getNode(Path);
            if (node != null) {
                
                node.setProperty("AEM Node", "shanu");
                node.setProperty("Get Job", "Aem Developer");
            }

            session.save();


            Resource resource = resolver.getResource("/content/hm/ca/jcr:content");
            

            if (resource != null) {

            ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
            if (modifiableValueMap !=null) {

            modifiableValueMap.put("Shantam", "Somesh");
            modifiableValueMap.put("Age", "20");
            modifiableValueMap.put("City", "Bangalore");
            modifiableValueMap.replace("jcr:title", "Canada");
            
                
            }
            
              resolver.commit(); 
            }
          
            

            log.info("......Servlet is Running....... ");
            log.info("New property addeed at" + Path);
            
        } catch (Exception e) {
            log.error("Exception Occur ", e);
        }

          servletResponse.getWriter().println(status);
          
      


    }



    
}
