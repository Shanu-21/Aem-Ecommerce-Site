package com.hm.aem.core.service.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Salesforce Integration Configuration", 
description = "Configuration for AEM to Salesforce Case Integration")
public @interface SalesforceConfig {

    @AttributeDefinition(name = "SalesForce Token URL")
    String tokenUrl() default "www.saleforce.com";

    @AttributeDefinition(name = "Salesforce API Version")
    String apiVersion() default "v60.0";

    @AttributeDefinition(name = "Client ID")
    String clientId();

    @AttributeDefinition(name = "Client Secret")
    String clientSecret();
    
}
