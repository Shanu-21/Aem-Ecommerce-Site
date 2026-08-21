package com.hm.aem.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.hm.aem.core.service.SendMessage;

@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyModel2 {

    @OSGiService
    SendMessage sendMessage;


    @Inject
    private String message2;

    public String getMessage2() {
         
        message2 = sendMessage.sendMessagetoUser();

        return message2;
    }

    
}
