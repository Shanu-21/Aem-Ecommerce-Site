package com.hm.aem.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import org.apache.commons.lang3.StringUtils;


@Component(service = Servlet.class)
@SlingServletPaths(
    value = { "/apps/workFlow"} 
    )


public class ExecuteWorkflow extends SlingSafeMethodsServlet{

        private static final Logger log = LoggerFactory.getLogger(ExecuteWorkflow.class);


    @Override
    protected void doGet( SlingHttpServletRequest req , SlingHttpServletResponse res)
    throws ServletException, IOException
    
    {
        String status = "workflow running";
        ResourceResolver resourceResolver = req.getResourceResolver();

        String payload = req.getRequestParameter("page").getString();
        
        try{
            if (StringUtils.isNotBlank(payload)) {

                WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);

                WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/hm-page-version");
                
                WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);
              status = workflowSession.startWorkflow(workflowModel, workflowData).getState();


            }
        }
            catch(Exception e){
                log.info("Exception Occurred:{}",e.getMessage());

            }
        
            res.setContentType("application/Json");
            res.getWriter().write(status);

    } 


}
