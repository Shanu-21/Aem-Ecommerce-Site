package com.hm.aem.core.service.config;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label ="HM Context" , description = "HM Context aware Configuration")
public @interface HmConfigs{

   @Property(label = "hmsite", description = "HM Site name" )
   String getHmSite() default "ey.com";
   
   @Property(label = "hmcountry", description = "HM Country name")
   String getHmCountry() default "global";

   @Property(label = "hmlocale", description = "HM country locale name" )
   String getHmLocale()  default "english";


    
}
