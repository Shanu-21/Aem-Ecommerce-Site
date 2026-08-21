package com.hm.aem.core.service.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.hm.aem.core.dtobeans.RelatedArticleDto;


@Component(service = RelatedPagesService.class)
public class RelatedPagesService implements RelatedPagesImp{

    @Reference
    QueryBuilder queryBuilderArticle;

 private final Logger logger = LoggerFactory.getLogger(RelatedPagesService.class);

    @Override
   public List<RelatedArticleDto> getRealtedArtcile(String rootPath, String maxResult ,Page Currentpage)
    {
        List<RelatedArticleDto> articles = new ArrayList<>();

        try {
    String[] tags = Currentpage.getProperties().get("cq:tags", String[].class);

    if (tags == null) {

        return articles;
        
    }

    Map<String,String> map = new HashMap<>();
    
    map.put("path", rootPath );
    map.put("type", "cq:Page");
    map.put("1_property", "jcr:content/cq:tags");
    for(int i = 0; i<tags.length; i++){
        map.put("1_property." + (i+1) + "_value", tags[i]);
    }
    map.put("orderby", "@jcr:content/cq:lastModified");
    map.put("orderby.sort", "desc");
    map.put("p.limit", maxResult);


    ResourceResolver resolver = Currentpage.getContentResource().getResourceResolver();
    Session session = resolver.adaptTo(Session.class);
    Query query = queryBuilderArticle.createQuery(PredicateGroup.create(map),session);


    SearchResult result = query.getResult();

    for(Hit hit : result.getHits()){

        Resource resource = hit.getResource();
        Page page = resource.adaptTo(Page.class);

        if (page == null) {
            
            continue;
        }

        if (page.getPath().equals(Currentpage.getPath())) {

            continue;
            
        }

        articles.add(new RelatedArticleDto(page.getTitle(), page.getPath()));

    }

  } catch (Exception e) {
    // TODO: handle exception

    logger.error("Error fetching related Article content", e);
  }

  return articles;


    

    }
    
}
