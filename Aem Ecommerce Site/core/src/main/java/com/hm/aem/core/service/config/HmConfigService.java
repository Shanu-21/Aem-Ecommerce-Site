package com.hm.aem.core.service.config;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;


@Component(service = HmConfigService.class, configurationPolicy = ConfigurationPolicy.OPTIONAL , immediate = true)


public class HmConfigService  {
    

   
   

    private String hmSite1;

    @Activate
    public void activate(HmConfigs hmConfigs)
    {
        this.hmSite1 = hmConfigs.getHmSite();
        
        
    }


     public String getHmSite1() {
        return hmSite1;
    }

    






    
}
