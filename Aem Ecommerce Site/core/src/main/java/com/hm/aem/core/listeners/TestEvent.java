package com.hm.aem.core.listeners;


import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(service = EventListener.class, immediate = true)

public class TestEvent  implements EventListener{

    @Reference
    private SlingRepository slingRepository;

    private ObservationManager observationManager;
    private Session session;

    private static final Logger log = LoggerFactory.getLogger(TestEvent.class);

    @Activate
    public void activate() throws RepositoryException {

        try {
            
            session = slingRepository.loginService("event-service-listener", null);

            observationManager = session.getWorkspace().getObservationManager();

            observationManager.addEventListener(
                                
            this,
            Event.PROPERTY_ADDED | Event.PROPERTY_CHANGED | Event.PROPERTY_REMOVED,
            "/content/hm/us/en/home-page",
             true, 
             null,
             null,
              false
            
            );

            log.info("----Event Found----");

        } catch (RepositoryException e) {
            
            log.info("Respository Exception Occured = " ,  e);
        }
    }


     @Deactivate
    protected void deactivate() {
        // Cleanup
        if (session != null && session.isLive()) {
            try {
                session.logout();
                log.info("JCR session logged out successfully.");
            } catch (Exception e) {
                log.warn("Error while logging out JCR session: ", e);
            }
        }
    }


    @Override
    public void onEvent(EventIterator eIterator){

        try {

            while (eIterator.hasNext()) {

            Event event = eIterator.nextEvent();

        if (event.getType() == Event.PROPERTY_ADDED) {
                log.info("New property added for " + event.getPath());
            }
                    
        if (event.getType() == Event.PROPERTY_REMOVED) {
             log.info("Property Removed on " + event.getPath());

             if (event.getType() == Event.PROPERTY_CHANGED) {
                log.info("Property is Changed on " + event.getPath());
             
                                

        }

        }
        
        }
            
        } catch (Exception e) {
            log.info("Excetion occured = ", e);
        }

        
    }




    
}
