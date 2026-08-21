package com.hm.aem.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.aem.core.service.SalesforceCaseService;

@Component(service = Servlet.class,
     property = {
    "sling.servlet.paths=/bin/support/create-case",
    "sling.servlet.methods= POST "})

public class SupportCaseServlet extends SlingAllMethodsServlet{


    @Reference
    private SalesforceCaseService salesforceCaseService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(SlingHttpServletRequest request , SlingHttpServletResponse response)
    throws IOException
    {

       response.setContentType("application/json");

        try {

            Map<String,String> caseData = new HashMap<>();
            caseData.put("name", request.getParameter("name"));
            caseData.put("email", request.getParameter("email"));
            caseData.put("subject", request.getParameter("subject"));
            caseData.put("description", request.getParameter("description"));

            validate(caseData);

            String caseID = salesforceCaseService.createCase(caseData);

            Map<String, Object> reponseMap = new HashMap<>();
            reponseMap.put("Success", true);
            reponseMap.put("Message", "Case Created Successfully");
            reponseMap.put("CaseID", caseID);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            objectMapper.writeValue(response.getWriter(), reponseMap);

                        
        } catch (Exception e) {
            response.setStatus(500);
             Map<String, Object> reponseMapError = new HashMap<>();
            reponseMapError.put("Success", false);
            reponseMapError.put("Message", "Failed To Create Salesforce Case");
            reponseMapError.put("Error", e.getMessage());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            objectMapper.writeValue(response.getWriter(), reponseMapError);
        }

        

    }

    private void validate(Map<String, String> data){

        if (isBlank(data.get("email")) || isBlank(data.get("subject"))){

            throw new IllegalArgumentException("Email and subject are Mandatory");
        }


    }

    private boolean isBlank(String value){

        return value == null || value.trim().isEmpty();
    }



    
}
