package com.hm.aem.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.dam.api.Asset;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;


@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TeamMembers {
    

  @SlingObject
  ResourceResolver resolver;
  
    @Inject
    private String membername;
    @Inject
    private String memberimage;
    @Inject
    private String memberdesgination;

    
    private String altText;
    private String imgTitle;

    
    public String getAltText() {
      return altText;
    }

    public String getImgTitle() {
      return imgTitle;
    }

    public String getMembername() {
        return membername;
    }

      public String getMemberdesgination() {
        return memberdesgination;
    }

      public String getMemberimage() {
        return memberimage;
    }


    @PostConstruct
    public void init(){

        try {

            if (memberimage != null) {
                
             Resource assetResource = resolver.getResource(memberimage);

             if (assetResource != null) {

              Asset asset = assetResource.adaptTo(Asset.class);

              if (asset !=null) {

             altText = asset.getMetadataValue("dc:description");
             imgTitle = asset.getMetadataValue("dc:title");

                
              }
          
              
             }
             
                
            }



            }       

           catch (Exception e) {
            // TODO: handle exception
        }
      }
  
   

    
}
