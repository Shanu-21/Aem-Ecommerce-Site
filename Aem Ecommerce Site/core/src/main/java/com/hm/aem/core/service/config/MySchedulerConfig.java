package com.hm.aem.core.service.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeType;

@ObjectClassDefinition(name = "HM MySchedulers Configuration" , description = "HM Schedulers information")

public @interface MySchedulerConfig {

    @AttributeDefinition(name = "Scheduler Name" , description = "Provide Name of the Schedulers" , type= AttributeType.STRING)
    String schedulerName() default "HM Scheduler";

    @AttributeDefinition(name = "Scheduler Enable", description = "Enable the Scheduler" , type = AttributeType.BOOLEAN)
    boolean schedulerEnable() default true;

    @AttributeDefinition(name = "Cron Experession" , description = "Please Provide the Cron Exp Details" , type = AttributeType.STRING)
    String schedulerExp() default "0 0/1 * 1/1 * ? *" ;
    
}
