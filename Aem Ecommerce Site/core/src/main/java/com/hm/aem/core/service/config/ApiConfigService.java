package com.hm.aem.core.service.config;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

@Designate(ocd = com.hm.aem.core.service.config.ApiConfig.class)
@Component(service = ApiConfigService.class , configurationPolicy = ConfigurationPolicy.OPTIONAL , immediate = true)


public class ApiConfigService {
    

    @Reference
    CardConfigService cardConfigService;

   
    private String apiURL;
    private String apiKey;

    @Activate
    public void activate(ApiConfig api)
    {
        this.apiURL = api.apiURL();
        this.apiKey = api.apiKey();
        cardConfigService.getOrganizationName();
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getApiKey() {
        return apiKey;
    }
}
