

package com.hm.aem.core.models;

import java.util.List;

import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class)
public class FaqComponent {

@ChildResource
@Named("faqlist")
private  List<FaqItems> itemlist;

/*private String[] num = new String[5];
private int[] kin = {1,2,3,4};

private String[] num2 = {"Shanu"};

 int n = num2.length;*/

public List<FaqItems> getItemlist() {
    return itemlist;

}
    
}