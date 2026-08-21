package com.hm.aem.core.servlets;

import java.io.IOException;

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

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/simpleform/submit")
public class SimpleFormServ extends SlingAllMethodsServlet {

    private static Logger log = LoggerFactory.getLogger(SimpleFormServ.class);


    @Override
    protected void doPost(SlingHttpServletRequest servletRequest , SlingHttpServletResponse servletResponse) 
    throws IOException, ServletException

{

    try {
        
   String name = servletRequest.getParameter("name");
   String email = servletRequest.getParameter("email");

   ResourceResolver resolver = servletRequest.getResourceResolver();

   Resource resource = resolver.getResource("/content/hm/us/en/test-form/jcr:content");

   if (resource != null) {

       ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
       modifiableValueMap.put("name", name);
       modifiableValueMap.put("EmailID", email);
       resolver.commit();
           
   }

   

   log.info("......Servlet is Running....... ");






 } catch (Exception e) {
        log.error("Error in servlet", e);
    }

servletResponse.getWriter().println("Servlet is running and the form details is subited");


    
}

}
