package com.hm.aem.core.service;

import org.osgi.service.component.annotations.Component;

@Component(service = SendMessage.class, property = {"type=model2"})
public class SendMessageImple2 implements SendMessage {


    @Override
    public String sendMessagetoUser(){

        return "This message to My mode2 Class User";
    }
    
}
