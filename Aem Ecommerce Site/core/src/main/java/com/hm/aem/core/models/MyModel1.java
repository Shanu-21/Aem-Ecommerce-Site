package com.hm.aem.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.hm.aem.core.service.SendMessage;

@Model(adaptables = Resource.class , defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL)
public class MyModel1 {

@OSGiService(filter = "(type=model2)")
SendMessage sendMessage;

@Inject
private String message;

public String getMessage() {

   message = sendMessage.sendMessagetoUser();

   return message;

    }
}

