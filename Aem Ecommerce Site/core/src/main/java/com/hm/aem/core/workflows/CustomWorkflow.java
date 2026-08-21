package com.hm.aem.core.workflows;


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


@Component(service = WorkflowProcess.class , immediate = true , 
property = {
    "process.label = hm-Custom-Workflow "
})

public class CustomWorkflow implements WorkflowProcess {


private static final Logger log = LoggerFactory.getLogger(CustomWorkflow.class);

@Override
public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException

{


    log.info("-----------------------------------test Custom hm wokflow------");
    
    try{
    WorkflowData workflowData = workItem.getWorkflowData();
    if(workflowData.getPayload().equals("JCR_PATH"))
    {
        Session session = workflowSession.adaptTo(Session.class);
        String Path = workflowData.getPayload().toString() + "/jcr:content";
        Node node = session.getNode(Path);
        if(node!=null){
        String[] processArgs = metaDataMap.get("PROCESS_ARGS", "string").toString().split(",");
        for(String w : processArgs){
            String[] p = w.split(":");
            String Name1 = p[0];
            String Value1 = p[1];
            
                node.setProperty(Name1,Value1);
                node.setProperty("aem training", "aem 6.5");
                session.save();
                log.info("New custom metadata field added");
            }

        }
    
    
    }  
        

}catch (RepositoryException e) {
            log.info("An I/O error occurred: " + e.getMessage());






}


}

}









