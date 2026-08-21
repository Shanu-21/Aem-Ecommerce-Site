package com.hm.aem.core.service.config;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;


@Designate(ocd = com.hm.aem.core.service.config.StudentConfig.class)
@Component(service = StudentConfigService.class,configurationPolicy = ConfigurationPolicy.OPTIONAL,immediate = true)

public class StudentConfigService {


    private String studentName;
    private int rollNumber;
    private boolean regular;
    private String[] subject;
    private String countries;

    @Activate
    public void activate(StudentConfig Stu)
    {
        this.studentName=Stu.studentName();
        this.rollNumber = Stu.rollNumber();
        this.regular = Stu.regBoolean();
        this.subject = Stu.subject();
        this.countries = Stu.countries(); 
    }



    public String getStudentName() {
        return studentName;
    }
    public int getRollNumber() {
        return rollNumber;
    }
    public boolean isRegular() {
        return regular;
    }
    public String[] getSubject() {
        return subject;
    }
    public String getCountries() {
        return countries;
    }

    
}
