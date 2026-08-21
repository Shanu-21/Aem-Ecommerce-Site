package com.hm.aem.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductItem {
    
    @Inject
    private String producttitle;

    @Inject
    private String productimage;


    public String getProducttitle() {
        return producttitle;
    }

    public String getProductimage() {
        return productimage;
    }
    
}
