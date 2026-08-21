package com.hm.aem.core.service.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Api URL Configuration" , description = "Configuration for API URL and runmode configuration")

public @interface ApiConfig {

    @AttributeDefinition(name = "API URL" , description = "The Base URL for the API")
    String apiURL() default "http://localhost:8080/api";

    @AttributeDefinition(name = "API Key" , description = "The runmode in which application is running")
    String apiKey() default "testKey";


    
}
