package com.hm.aem.core.service.config;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;


@ObjectClassDefinition(name = "Student Details" , description = "Enter your Students details here")
public @interface StudentConfig {

@AttributeDefinition(name = "Student Name" , description = "Name of the Student", type = AttributeType.STRING)
String studentName() default ("Enter Your name"); 


@AttributeDefinition(name = "Roll Number" , description = "Student Roll Number", type = AttributeType.INTEGER)
int rollNumber() default 0000;

@AttributeDefinition(name = "Regular" , description = "Is Student is Regular" , type = AttributeType.BOOLEAN)
boolean regBoolean() default true;

@AttributeDefinition(name = "Subject" , description = "See Your Subjects" , type = AttributeType.STRING)
String[] subject() default {"math","english","Science","Hindi"};


@AttributeDefinition(name =  "Countries", type = AttributeType.STRING,
                        description = "Select your Countries",
                        options = {
                            @Option(label = "India", value = "india"),
                            @Option(label = "Russia", value = "russia"),
                            @Option(label = "France", value = "france"),
                            @Option(label = "America", value = "america")
                        })
    public String countries() default "India";  
}
