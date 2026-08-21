package com.hm.aem.core.models;

import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.hm.aem.core.service.config.CardConfigService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardModel {


    @OSGiService 
    CardConfigService cardConfigService;

    @ValueMapValue 
    @Named("cardtitle")
    private String cardTitle;

    @ValueMapValue
    @Named("buttontext")
    private String buttonText;


    public String getOrganizationName() {
        return cardConfigService.getOrganizationName();
    }

    public String getHomePageUrl() {
        return cardConfigService.getHomePageUrl();
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getButtonText() {
        return buttonText;
    }

    
}
