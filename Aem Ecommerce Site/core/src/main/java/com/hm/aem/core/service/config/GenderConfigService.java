package com.hm.aem.core.service.config;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.component.annotations.Activate;


@Designate(ocd = GenderConfig.class)
@Component(service = GenderConfigService.class , immediate = true)
public class GenderConfigService {

    private String genderAPI;

   

    @Activate
    public void Activate(GenderConfig config){

        this.genderAPI = config.genderapiURL();
    }


     public String getGenderAPI() {
        return genderAPI;
    }


    
}
