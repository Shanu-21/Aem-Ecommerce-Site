package com.hm.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SimpleForm {
    
    @ValueMapValue
    private String formtitle;

    @ValueMapValue
    private String thankyoumessage;


    public String getFormtitle() {
        return formtitle;
    }


    public String getThankyoumessage() {
        return thankyoumessage;
    }


    public String getActionPath(){

    return "/bin/simpleform/submit";

    }
    
}
