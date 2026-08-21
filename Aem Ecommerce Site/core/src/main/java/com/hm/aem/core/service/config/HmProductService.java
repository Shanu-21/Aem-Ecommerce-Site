package com.hm.aem.core.service.config;

import com.hm.aem.core.dtobeans.HmProductDto;

public interface HmProductService {

    HmProductDto getProductDetails(String productId);
    
}
