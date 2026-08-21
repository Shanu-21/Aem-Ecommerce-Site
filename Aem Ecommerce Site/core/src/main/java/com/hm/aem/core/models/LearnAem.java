package com.hm.aem.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LearnAem {
    
        @SlingObject
        ResourceResolver resolver;

        @Self
        Resource currentResource;
    

    private String myData;
   private  String pagePath;
  private  String pageTitle;
   private  List<String>mychildData = new ArrayList<>();

     public List<String> getMychildData() {
        return mychildData;
    }

     public String getMyData() {
        return myData;
    }




    public String getPagePath() {
        return pagePath;
    }




    public String getPageTitle() {
        return pageTitle;
    }




     @PostConstruct
    protected void init()
{
    Resource resource = resolver.getResource("/content/hm/us/en/jcr:content");
    ResourceResolver resolver2 = resource.getResourceResolver();//getting same resolver of the given path
    if (resource !=null) {

        ValueMap value = resource.getValueMap();
    myData = value.get("jcr:title", String.class);

Resource resourcechild = resolver2.getResource("/content/hm/us/en");
     for(Resource resource2 : resourcechild.getChildren()){
    
            Resource childresource = resource2.getChild("jcr:content");

            if (childresource != null) {
                ValueMap childvalue = childresource.getValueMap();
            String childdata = childvalue.get("jcr:title", String.class);

            if (childdata !=null) {
                
                mychildData.add(childdata);
            }
            }
            
    }
   

    PageManager pageManager = resolver.adaptTo(PageManager.class);
    if (pageManager !=null) {
        Page page = pageManager.getContainingPage(currentResource);

        if (page != null) {

    pagePath = page.getPath();
    pageTitle = page.getName();
        
    }
    }

    
   
}
  

}
}

