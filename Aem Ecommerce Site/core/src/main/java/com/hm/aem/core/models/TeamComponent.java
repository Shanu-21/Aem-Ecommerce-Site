package com.hm.aem.core.models;

import java.util.List;


import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.api.resource.Resource;



@Model(adaptables = Resource.class , defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TeamComponent {

    

    @ChildResource
    private List<TeamMembers> teammember;



    public List<TeamMembers> getTeammember() {
        return teammember;

    }

    

        

    }





