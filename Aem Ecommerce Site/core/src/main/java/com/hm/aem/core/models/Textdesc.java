package com.hm.aem.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.hm.aem.core.service.config.StudentConfigService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Textdesc {

@OSGiService
StudentConfigService student;

    @Inject
    @Required
    @Default(values = "HM Projects")
    private String title;
    @Inject
    private String gender;

    @Inject
    private String studentName;
    @Inject
    private int rollNumber;
    @Inject
    private boolean regular;
    @Inject
    private String[] subject;
    @Inject
    private String countries;

    public String getStudentName() {
        return student.getStudentName();
    }

    public int getRollNumber() {
        return student.getRollNumber();
    }

    public boolean isRegular() {
        return student.isRegular();
    }

    public String[] getSubject() {
        return student.getSubject();
    }

    public String getCountries() {
        return student.getCountries();
    }

    public String getTitle() {

        if (title == null){
            return ("Title is not Set");
        }
        return title.toUpperCase();

    }
    
    public String getGender() {
        return gender;
    }

   
    
    
}
