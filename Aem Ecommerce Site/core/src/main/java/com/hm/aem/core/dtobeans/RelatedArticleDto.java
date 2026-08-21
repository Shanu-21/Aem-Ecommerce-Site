package com.hm.aem.core.dtobeans;


public class RelatedArticleDto{


private String title;
private String path;



public RelatedArticleDto(String pageTitle,String pagePath){

    this.title = pageTitle;
    this.path = pagePath;

}


public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }


}

