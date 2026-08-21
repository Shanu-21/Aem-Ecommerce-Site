package com.hm.aem.core.workflows;


import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {"process.label=UpdateWorkflowMetadata"})

public class UpdateWorkflowMetadata implements WorkflowProcess
{

private static final Logger log = LoggerFactory.getLogger(UpdateWorkflowMetadata.class);




@Override
public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)throws WorkflowException
{

     String status;

    try {

        status = metaDataMap.get("status", "Approved");

        WorkflowData workflowData = workItem.getWorkflowData();
        MetaDataMap addMetaDataMap = workflowData.getMetaDataMap();
        addMetaDataMap.put("status", status);


        log.info("Step args added to workflow metadata {}",status);


    
        
    } catch (Exception e) {
        log.error("Workflow Exception Occur", e);
    }


}




    
}

