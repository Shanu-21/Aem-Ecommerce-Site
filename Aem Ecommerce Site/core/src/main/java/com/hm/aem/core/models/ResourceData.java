package com.hm.aem.core.models;

import java.util.ArrayList;
import java.util.*;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResourceData {


 @SlingObject
 ResourceResolver resolver;

 private String pagePath = "/content/hm/us/en/";

 private static final Logger log = LoggerFactory.getLogger(ResourceData.class);
 
  private String myResource;
  private String myChildResource;

  private List<String> myNewpage = new ArrayList<>();



  public List<String> getMyNewpage() {
    return myNewpage;
}


  

 
  public String getMyResource() {
    return myResource;
  }
  public String getMyChildResource() {
    return myChildResource;
  }
  

@PostConstruct
public void init(){

    try {
        Resource r1 = resolver.getResource(pagePath + "jcr:content");  // for parent page
        if (r1!=null){

        ValueMap valueMap = r1.getValueMap();        
        myResource = valueMap.get("jcr:title", String.class);

        Resource childResource = resolver.getResource(pagePath + "home-page/jcr:content");//specific child page
        ValueMap child = childResource.getValueMap();
        if (child !=null) {
        myChildResource = child.get("jcr:title", String.class);
            
        }

        else{
            log.error("Child resource not found");
        }

        Resource resource = resolver.getResource(pagePath); //all the child page in this page path.
        if (resource != null) {
            
        
        for(Resource allChild : resource.getChildren()){

            Resource child2 = allChild.getChild("jcr:content");
            if (child2!=null) {
                ValueMap v1 = child2.getValueMap();
                String p1 = v1.get("jcr:title",String.class);
                 if (p1 !=null) {
                    myNewpage.add(p1);
                 }
            }
        }

    }

    }


    
    else{
        log.error("Resorce data not found");
    }

        


    } catch (Exception e) {

        log.info("error fecthing resource data" , e);
    }
}
    
}
