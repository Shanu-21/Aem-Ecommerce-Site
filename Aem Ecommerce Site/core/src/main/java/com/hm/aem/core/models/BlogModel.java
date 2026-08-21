package com.hm.aem.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.hm.aem.core.service.config.BlogService;


@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogModel {


@OSGiService
BlogService blogService;

@ValueMapValue
private String blogRoot;

@ValueMapValue
private Integer limit;

public String getBlogRoot() {
    return blogRoot;
}

public Integer getLimit() {
    return limit;
}

private List<BlogItem> blogList;


@PostConstruct
public void init(){

    if (blogRoot!=null && limit!=null ) {

    blogList = blogService.getRecentPublishedPages(blogRoot,limit);

        
    }


}




public List<BlogItem> getBlogList() {
    return blogList;
}




public static class BlogItem {

    private String path;
    private String title;
    private String publishedDate;

    
    public BlogItem(String path, String title, String publishedDate) {
        this.path = path;
        this.title = title;
        this.publishedDate = publishedDate;
    }
    public String getPath() {
        return path;
    }
    public String getTitle() {
        return title;
    }
    public String getPublishedDate() {
        return publishedDate;
    }

}

}
