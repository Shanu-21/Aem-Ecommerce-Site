package com.hm.aem.core.service.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Card Configuration" , description = "Card Configuration Detsils")
public @interface CardConfig {

@AttributeDefinition(name = "Organization Name" , description = " Name of the Organization", type = AttributeType.STRING)
    String getOrganizationName();

    @AttributeDefinition(name = "HomePage URL" ,description = "Home Page URL website")
    String getHomePageUrl();

    
}
