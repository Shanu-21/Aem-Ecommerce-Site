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

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;


@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageManagerData {

@Self
Resource resource;

private String pagepath;
private String pagename;
private String p1;
private String p2;
List<String> titlepage = new ArrayList<>();


public List<String> getTitlepage() {
    return titlepage;
}
public String getPagepath() {
    return pagepath;
}
public String getPagename() {
    return pagename;
}

public String getP1() {
    return p1;
}
public String getP2() {
    return p2;
}


@PostConstruct
public void init(){

    try {


        ResourceResolver resolver = resource.getResourceResolver();
        

       PageManager pageManager = resolver.adaptTo(PageManager.class);

        if (pageManager != null) {
            Page currentPage = pageManager.getContainingPage(resource);
            
          
            if (currentPage != null) {
                            
        this.pagename = currentPage.getName();
        this.pagepath = currentPage.getPath();
        
            }
        }
                  Resource r5 = resolver.getResource("/content/hm");

                  if (r5 !=null) {
                    p1 = r5.getName();
                   p2 = r5.getPath();

for(Resource p4 : r5.getChildren()){
  Resource p5 = p4.getChild("jcr:content");

  if (p5 != null) {
  ValueMap valueMap1 = p5.getValueMap();
String new1 = valueMap1.get("jcr:title", String.class);
if (new1 !=null) {
titlepage.add(new1);
}

  }

}
                  }



        
    
    } catch (Exception e) {
        // TODO: handle exception
    }

}
  
}







