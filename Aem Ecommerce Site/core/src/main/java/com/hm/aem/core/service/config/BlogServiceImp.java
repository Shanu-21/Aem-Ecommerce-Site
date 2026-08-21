package com.hm.aem.core.service.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.hm.aem.core.models.BlogModel.BlogItem;

@Component(service = BlogService.class)
public class BlogServiceImp implements BlogService {

    @Reference
    QueryBuilder queryBuilder;

    @Reference
    private ResourceResolverFactory resolverFactory;


    @Override
    public List<BlogItem> getRecentPublishedPages(String PageRoot , Integer pageLimit)

    {
        List<BlogItem> blogPages = new ArrayList<>();

        final Logger log = LoggerFactory.getLogger(BlogServiceImp.class);

        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "event-service-listener");


        try {

        ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param);

            
        if (resolver !=null) {
            
        
        Session session = resolver.adaptTo(Session.class);
        
        Map<String,String> map = new HashMap<String,String>();

        map.put("path", PageRoot);
        map.put("type", "cq:Page");
        map.put("orderby", "@jcr:content/cq:lastReplicated");
        map.put("orderby.sort", "desc");
        map.put("p.limit", String.valueOf(pageLimit));
        map.put("property", "jcr:content/cq:lastReplicationAction");
        map.put("property.value", "Activate");
        
        Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
        SearchResult result = query.getResult();

        for(Hit hit : result.getHits()){

            String pagePath = hit.getPath();

            Resource pageResource = resolver.getResource(pagePath + "/jcr:content");
            ValueMap valueMap = pageResource.getValueMap();
            String pageTitle = valueMap.get("jcr:title", String.class);
            //String publishDate = valueMap.get("cq:lastReplicated", String.class);

        
            blogPages.add(new BlogItem(pagePath, pageTitle, "01/01/2026"));
        
           

        }

    }


        } catch (Exception e) {
            // TODO: handle exception

            log.error("Error while fetching blog pages", e);

        }
      
        return blogPages;


    }
    
}
