package com.hm.aem.core.models;


import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.hm.aem.core.service.config.ApiConfigService;

@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserDetails {

    @OSGiService 
    ApiConfigService apiosgiservice;

    @ValueMapValue
    private String username;

    @ValueMapValue
    private String userdesignation;

    @ValueMapValue
    @Named("contact")
    private String usercontact;

    @ValueMapValue
    private String assetsfolder;

    @ValueMapValue
    private String gender;

        
    public String getApiKey()
    {
        return apiosgiservice.getApiKey();
    }

    public String getApiUrl() {
        return apiosgiservice.getApiURL();
    }

    public String getUsername() {
        return username;
    }

    public String getUserdesignation() {
        return userdesignation;
    }

    public String getUsercontact() {
        return usercontact;
    }

    public String getAssetsfolder() {
        return assetsfolder;
    }

    public String getGender() {
        return gender;
    }



    
}
