package com.hm.aem.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = org.apache.sling.api.resource.Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestModel {

    private static final Logger log = LoggerFactory.getLogger(TestModel.class);

    private String message;

    public String getMessage() {
        return message;
    }

    @PostConstruct
    protected void init() {
        message = "✅ TestModel initialized successfully!";
        log.info("✅ TestModel INIT: {}", message);
    }
}