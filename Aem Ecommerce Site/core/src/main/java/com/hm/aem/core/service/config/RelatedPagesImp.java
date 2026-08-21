package com.hm.aem.core.service.config;

import java.util.List;

import com.day.cq.wcm.api.Page;
import com.hm.aem.core.dtobeans.RelatedArticleDto;


public interface RelatedPagesImp {

    List<RelatedArticleDto> getRealtedArtcile(String rootPath , 
                             String maxResult ,Page Currentpage);


    
}
