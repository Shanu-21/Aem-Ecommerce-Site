package com.hm.aem.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.aem.core.service.config.MySchedulerConfig;

@Designate(ocd = MySchedulerConfig.class)
@Component(service = Runnable.class, immediate = true)

public class MyScheduler implements Runnable{

    public static final Logger log = LoggerFactory.getLogger(MyScheduler.class);

   @Reference
   Scheduler scheduler;

   @Activate
   protected void Activate(MySchedulerConfig mySchedulerConfig){
 
    log.info("---My HM Scheduler is executed Succesfully-------");
    addScheduler(mySchedulerConfig);
   
   }

   @Deactivate
protected void Deactivate(MySchedulerConfig mySchedulerConfig)
{


    log.info("-----Scheduler is deactivated---------");
    removeScheduler(mySchedulerConfig);
}

   public void removeScheduler(MySchedulerConfig mySchedulerConfig)
   {
    try{
    scheduler.unschedule(mySchedulerConfig.schedulerName());
    }
    catch(Exception e){
        log.error("------Exception occur-----" + e);
    }
   }

   
   public void addScheduler(MySchedulerConfig mySchedulerConfig){
    
    log.info("----------Scheduler Added Succesfully---------");
    if(mySchedulerConfig.schedulerEnable()){

     ScheduleOptions scheduleOptions = scheduler.EXPR(mySchedulerConfig.schedulerExp());
     scheduleOptions.name(mySchedulerConfig.schedulerName());
     scheduler.schedule(this, scheduleOptions);
     log.info("------Scheduler added Succesfully------- : " + mySchedulerConfig.schedulerName());
     
    }
    else{

        log.error("----Scheduler Not added------");

    }
    }
   

    @Override
    public void run(){

        log.info("-----My Hm Scheduler is Extecuted and Running -----------");

    }
  
    
}


