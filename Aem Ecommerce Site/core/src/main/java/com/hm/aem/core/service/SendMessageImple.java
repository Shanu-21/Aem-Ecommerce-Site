package com.hm.aem.core.service;

import org.osgi.service.component.annotations.Component;

@Component(service = SendMessage.class ,property = {"type=model1"})
public class SendMessageImple implements SendMessage{


    @Override
    public String sendMessagetoUser(){

        return "This message to My mode1 Class User";
    }
    
}
