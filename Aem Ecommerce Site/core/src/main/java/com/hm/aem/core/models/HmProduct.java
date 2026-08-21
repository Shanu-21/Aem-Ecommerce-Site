package com.hm.aem.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.hm.aem.core.dtobeans.HmProductDto;
import com.hm.aem.core.service.config.HmProductService;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HmProduct {

@SlingObject
ResourceResolver resolver;

@OSGiService
HmProductService hmProductService;
    
@ValueMapValue
private String productId;

@ValueMapValue
private String productCf;

private String productDescription;
private String hmProductImage;

private HmProductDto hmProductDetailsDto;

@PostConstruct
public void init(){

    try {

    Resource resource = resolver.getResource(productCf);
    ContentFragment fragment = resource.adaptTo(ContentFragment.class);
    
    if (fragment!=null) {

    productDescription = fragment.getElement("productDescription").getContent();
    hmProductImage = fragment.getElement("hmProductImage").getContent();
      
    }

    if (productId!=null) {

    hmProductDetailsDto = hmProductService.getProductDetails(productId);

        
    }
     
        
    } catch (Exception e) {
        // TODO: handle exception
    }

}

public String getProductDescription() {
    return productDescription;
}

public String getHmProductImage() {
    return hmProductImage;
}

public String getProductId() {
    return productId;
}

public HmProductDto getHmProductDetailsDto() {
    return hmProductDetailsDto;
}


}
