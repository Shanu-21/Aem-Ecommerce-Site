package com.hm.aem.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.wcm.api.Page;
import com.hm.aem.core.service.config.HmConfigService;
import com.hm.aem.core.service.config.HmConfigs;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ContextAwareModel {

    private static final Logger log = LoggerFactory.getLogger(ContextAwareModel.class);

    @ScriptVariable
    private Page currPage;

    @SlingObject
    private Resource resource;

    @OSGiService
   HmConfigService hmConfigService;

 

    private String hmSite;
    private String hmCountry;
    private String hmLocale;

    

    public String getHmSite() {
        
        return hmConfigService.getHmSite1();
    }



    public String getHmCountry() {
        return hmCountry;
    }



    public String getHmLocale() {
        return hmLocale;
    }



    @PostConstruct
    protected void init() {

        try {

            log.info("DEBUG: Page Path = {}", currPage.getPath());
            log.info("DEBUG: Component Resource = {}", resource.getPath());



            // Use the adaptable resource (recommended for CAC)
            ConfigurationBuilder configBuilder = resource.adaptTo(ConfigurationBuilder.class);

            log.info("DEBUG: ConfigurationBuilder exists? {}", configBuilder != null);

            if (configBuilder != null) {

                HmConfigs hmConfig = configBuilder.as(HmConfigs.class);

                log.info("DEBUG: hmConfig exists? {}", hmConfig != null);

                if (hmConfig != null) {

                    
                    hmCountry = hmConfig.getHmCountry();
                    hmLocale = hmConfig.getHmLocale();

                    log.info("DEBUG: CAC Resolved Values - Site: {} | Country: {} | Locale: {}",
                            hmSite, hmCountry, hmLocale);
                }
            }

        } catch (Exception e) {
            log.error("Error while loading Context-Aware Configurations", e);
        }
    }
}
