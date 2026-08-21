package com.hm.aem.core.service.config;

import javax.jcr.Node;
import javax.jcr.Session;


import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;


@Component(service = WorkflowProcess.class , property = {"process.label = UpdateNodeProperties"})
public class UpdateNodeProperties implements WorkflowProcess{

public static final  Logger logger = LoggerFactory.getLogger(UpdateNodeProperties.class);

@Reference
ResourceResolver resolver;

   
@Override
public void execute(WorkItem workflowItem, WorkflowSession workflowSession, MetaDataMap metadata)
 throws WorkflowException

 {
   WorkflowData workflowData = workflowItem.getWorkflowData();
   String payload = workflowData.getPayload().toString() + "/jcr:content";

   try {


    if (payload != null) {

     /*  Resource resource = resolver.getResource(payload);
      ModifiableValueMap map = resource.adaptTo(ModifiableValueMap.class);

      map.put("newpropert", "Shanu");

      resolver.commit();*/


       Session session = workflowSession.adaptTo(Session.class);
       Node node = session.getNode(payload);
       node.setProperty("testName", "Shantam Somesh");
       node.setProperty("testAge", "21");
       node.setProperty("testPhone", "8660941017");

       session.save();
   } 

    
   } catch (Exception e) {
    // TODO: handle exception
    logger.error("Exception found - Unable to process", e);
   }

   





 }




}
