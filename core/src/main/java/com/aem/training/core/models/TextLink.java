package com.aem.training.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy =  DefaultInjectionStrategy.OPTIONAL)
public class TextLink {

    @Inject
    String variation;

    @ValueMapValue (injectionStrategy = InjectionStrategy.REQUIRED)
    @Default(values = "Click Here")
    String linkLabel;

    @ValueMapValue
    String linkUrl;

    @ValueMapValue
    @Named("target")
    String linkTarget;

    public String getVariation() {
        return variation;
    }

    public String getLinkLabel() {
        return linkLabel;
    }

    public String getLinkUrl() {
        if(linkUrl == null || linkUrl.isEmpty()){
            return "#";
        }else if(linkUrl.startsWith("/content/aem-training")){
            return linkUrl + ".html";
        }
        return linkUrl;
    }

    public String getLinkTarget() {
        return linkTarget;
    }

    public boolean isOpenInNewTab(){
        return "_blank".equals(linkTarget);
    }
    

}
