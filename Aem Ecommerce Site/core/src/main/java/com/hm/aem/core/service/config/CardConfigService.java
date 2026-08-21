package com.hm.aem.core.service.config;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;

@Designate(ocd = com.hm.aem.core.service.config.CardConfig.class)

@Component(service = CardConfigService.class , configurationPolicy = ConfigurationPolicy.OPTIONAL)
public class CardConfigService {

    private String OrganizationName;
    private String HomePageUrl;


    @Activate
    public void activate(CardConfig cardconfig){

        this.OrganizationName = cardconfig.getOrganizationName();
        this.HomePageUrl = cardconfig.getHomePageUrl();
    }


    public String getOrganizationName() {
        return OrganizationName;
    }
    public String getHomePageUrl() {
        return HomePageUrl;
    }

 
    
}
