package com.hm.aem.core.models;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ShowPdfModel {

@ValueMapValue
private String pdfTitle;

@ValueMapValue
private String pdfPath;

@ValueMapValue
private boolean inlineView;

@ValueMapValue
private boolean showDownload;

@ValueMapValue
private boolean openinNewTab;

private boolean validPdf;

public String getPdfTitle() {
    return pdfTitle;
}

public String getPdfPath() {
    return pdfPath;
}

public boolean isInlineView() {
    return inlineView;
}

public boolean isShowDownload() {
    return showDownload;
}

public boolean isOpeninNewTab() {
    return openinNewTab;
}


public boolean isValidPdf() {
    return validPdf;
}

@PostConstruct
protected void init(){

    validPdf = StringUtils.isNotBlank(pdfPath) && pdfPath.toLowerCase().endsWith(".pdf");
}
    
}
