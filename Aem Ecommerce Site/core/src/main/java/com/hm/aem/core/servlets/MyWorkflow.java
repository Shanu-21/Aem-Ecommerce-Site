package com.hm.aem.core.servlets;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;


@Component(service = WorkflowProcess.class, 
property = {"process.label = My Workflow "})

public class MyWorkflow implements WorkflowProcess {

    private static final  Logger log = LoggerFactory.getLogger(MyWorkflow.class);

    @Override
    public void execute(WorkItem item, WorkflowSession workflowSession, MetaDataMap dataMap) throws WorkflowException {
        // TODO Auto-generated method stub

    WorkflowData workflowData = item.getWorkflowData();
    if(workflowData.getPayload().equals("JCR_PATH"));
    {
        String path = workflowData.getPayload().toString() + "/jcr:content";
        
        
        
        try{
        
            Session jcrSession = workflowSession.adaptTo(Session.class);
            Node node = jcrSession.getNode(path);
            if(node != null){
                node.setProperty("myworkflow", 1235);
                node.setProperty("Approver" , "shantam");
                node.setProperty("Approver1" , setValue(dataMap));

                jcrSession.save();
                log.info("Workflow executed and completed");
            }
         
        }catch(RepositoryException e){
            throw new WorkflowException(e.getMessage(), e);
        }
    }

}

private String setValue(MetaDataMap dataMap){
    if(dataMap.containsKey("PROCESS_ARGS")){
        return dataMap.get("PROCESS_ARGS", String.class);
    }
    return "No Arguments";
}


    
}
