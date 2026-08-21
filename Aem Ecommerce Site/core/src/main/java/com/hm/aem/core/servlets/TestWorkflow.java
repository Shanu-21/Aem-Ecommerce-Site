package com.hm.aem.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;


@Component(service = Servlet.class )
@SlingServletPaths( value = { "/apps/publishWorkflow"})
public class TestWorkflow extends SlingSafeMethodsServlet{

    private static final Logger log = LoggerFactory.getLogger(TestWorkflow.class);
    
    @Override
protected void doGet(SlingHttpServletRequest request , SlingHttpServletResponse response) throws IOException, ServletException{

    
    String parameter = request.getParameter("PageName").toString();
    ResourceResolver resolver = request.getResourceResolver();

    try {
        
    WorkflowSession session = resolver.adaptTo(WorkflowSession.class);
    WorkflowModel model = session.getModel("/var/workflow/models/request-for-publication");
    WorkflowData data = session.newWorkflowData("JCR_PATH", parameter);
    session.startWorkflow(model, data);

    } catch (Exception e) {
        // TODO: handle exception

        log.error("found error", e);
    }
    


}
    
}
