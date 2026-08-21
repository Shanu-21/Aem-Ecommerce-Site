package com.hm.aem.core.models;


import com.day.cq.wcm.api.Page;
import com.hm.aem.core.dtobeans.RelatedArticleDto;
import com.hm.aem.core.service.config.RelatedPagesService;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RelatedArticlePages {


@OSGiService
RelatedPagesService relatedPagesService;

@ScriptVariable
Page currentPage;


@ValueMapValue
private String rootPath;

@ValueMapValue
private String maxResult;

private List<RelatedArticleDto> tagResult;




@PostConstruct
protected void init(){

     tagResult = relatedPagesService.getRealtedArtcile(rootPath, maxResult, currentPage);

}

public List<RelatedArticleDto> getTagResult() {
    return tagResult;
}

public String getRootPath() {
    return rootPath;
}

public String getMaxResult() {
    return maxResult;
}



    
}
