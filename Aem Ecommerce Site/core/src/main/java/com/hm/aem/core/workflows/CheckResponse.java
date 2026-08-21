

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


@Component(service = WorkflowProcess.class, property = {"process.label=CheckResponse" })


public class CheckResponse implements WorkflowProcess{


private static final Logger log = LoggerFactory.getLogger(CheckResponse.class);

@Override
public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) 
throws WorkflowException {

try {

    MetaDataMap stepMetadata = workItem.getMetaDataMap();
    String response = stepMetadata.get("decision", String.class);


    if (response != null) {

    WorkflowData workflowData = workItem.getWorkflowData();
    MetaDataMap workMetaData = workflowData.getMetaDataMap();
    workMetaData.put("decision", response);

    log.info("The reposne from User Is {} ", response);


    }
    
} catch (Exception e) {
    // TODO: handle exception



}
}    
}
