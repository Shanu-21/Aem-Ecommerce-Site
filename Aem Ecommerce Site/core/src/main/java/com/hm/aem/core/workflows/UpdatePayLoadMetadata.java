package com.hm.aem.core.workflows;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {"process.label=UpdatePayloadMetadata"})

public class UpdatePayLoadMetadata implements WorkflowProcess{


private static final Logger log = LoggerFactory.getLogger(UpdatePayLoadMetadata.class);



@Override
public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
throws WorkflowException{

  String payload;

    try {
        
        WorkflowData workflowData = workItem.getWorkflowData();
        payload = workflowData.getPayload().toString()+ "/jcr:content";

        
      MetaDataMap workflowMetaDataMap = workflowData.getMetaDataMap();
       String approvalStatus = workflowMetaDataMap.get("status",String.class);

        log.info("The approval Status is : {}", approvalStatus);
        log.info("The Payload path is: {}", payload);
        

        Session session = workflowSession.adaptTo(Session.class);

        if (session != null) {
          
        
          Node node = session.getNode(payload);

          if (node != null) {            
          

        if (StringUtils.isNotBlank(approvalStatus) && "Approved".equals(approvalStatus)) {   // for comparing Strings

          node.setProperty("MarketingapprovalStatus", "Approved");              
        }
        else{

        node.setProperty("MarketingapprovalStatus", "Rejected");
        }

        }

        session.save();

        

           }
        

    } catch (Exception e) {
        // TODO: handle exception
        log.error("Workflow Exception Occur", e);
    }
       

}
    

}
