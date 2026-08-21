package com.hm.aem.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.model.WorkflowModel;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;


@Component(service = Servlet.class)
@SlingServletPaths( 
    value = {"/apps/publishWorkflow"}
    )

public class ExecutePublicationWorkflow extends SlingSafeMethodsServlet{
    
    private static final Logger log = LoggerFactory.getLogger(ExecutePublicationWorkflow.class);

@Override
protected void doGet(final SlingHttpServletRequest request , SlingHttpServletResponse response) 
throws ServletException, IOException 

{

    String status = "Start Running workflow";

    final ResourceResolver resourceResolver = request.getResourceResolver();
    String payload = request.getRequestParameter("Page").toString();

try{
    if(StringUtils.isNotBlank(payload)){

        WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
        WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/request-for-publication");
        WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH" , payload);
        status = workflowSession.startWorkflow(workflowModel, workflowData).getState();


    }


    }
    catch(Exception exception){
        log.info("Exception Occurred:{}",exception.getMessage());
}

response.setContentType("application/Json");
            response.getWriter().write(status);
}

}
