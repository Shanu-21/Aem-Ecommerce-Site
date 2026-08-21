package com.hm.aem.core.service.config;

import java.util.List;

import com.hm.aem.core.models.BlogModel.BlogItem;

public interface BlogService {

    List<BlogItem> getRecentPublishedPages(String PageRoot , Integer pageLimit);
    
}
