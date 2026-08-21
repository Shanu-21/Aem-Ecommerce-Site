package com.hm.aem.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import org.osgi.service.component.annotations.Reference;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Shan {
    
@Inject
@Via("request")
private String title;

@Reference
private ModelFactory modelFactory;

@SlingObject
Session session;


@ValueMapValue
@Default(values ="18")
private String age;


public String getAge() {
    return age;
}


@PostConstruct
private void init(){

    
}



}
