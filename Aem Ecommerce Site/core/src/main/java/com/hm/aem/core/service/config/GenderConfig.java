package com.hm.aem.core.service.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;



@ObjectClassDefinition(name= "Gender API Configuration" , description= "Gender APi Configuration")
public @interface GenderConfig {


    @AttributeDefinition(name = "Gender Api URL" , description = "Gender api url" )
    String genderapiURL() default  "https://api.genderize.io/?name=luc";

    
}
