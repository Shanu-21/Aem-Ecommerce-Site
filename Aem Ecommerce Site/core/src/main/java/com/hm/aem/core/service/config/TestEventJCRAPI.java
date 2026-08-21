/*package com.hm.aem.core.service.config;

import java.util.EventListener;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.ObservationManager;

import org.apache.sling.jcr.api.SlingRepository;
import org.eclipse.jetty.util.log.Log;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventListener.class, immediate = true)
public class TestEventJCRAPI implements EventListener{

    private Session session;
    private static final Logger log = LoggerFactory.getLogger(TestEventJCRAPI.class);


    @Reference
    SlingRepository slingRepository;

    @Reference
    ObservationManager observationManager;

    @Activate
    public void activate() throws Exception {

        try {
            String [] nodeType = {"cq:page"};
        session = slingRepository.loginService("admin", null);
        session.getWorkspace().getObservationManager().addEventListener( 
            this, 
            Event.NODE_ADDED|Event.PROPERTY_ADDED,
            "/content/hm/us/en",
            true,
            null,
            nodeType,
            false);

            log.info("Test Event Activated");

    
        
        }
        catch(RepositoryException e)
    {
        log.info("\n Error while adding Event Listener" , e.getMessage());
    }    

}

    public void onEvent(EventIterator eventIterator){  // we are using eventiterator not objects because there are multipe nodes will be crate for a page, 
                                                    //so this will content the event for all the multiple node. so it will get all details for teh event taht is perform.
        try {
              while (eventIterator.hasNext() && !eventIterator.hasNext()) {
                if (eventIterator.nextEvent() != null) {

                  log.info("\n Type: {} , Path : {} ", eventIterator.nextEvent().getType() , eventIterator.nextEvent().getPath());

                    
                }
    
            }

        }
        
        catch (Exception e1){
            log.error("\n Error while processing Events", e1.getMessage());
        }
    }
    
} 

*/
