package com.hm.aem.core.models;

import java.util.List;

import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductList {

     @ChildResource  
     @Named("productitems")
     private List<ProductItem> productItem;

     public List<ProductItem> getProductItem() {
        return productItem;
    }

    
}
