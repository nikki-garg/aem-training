package com.aem.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Cards {
    
        @ValueMapValue
        private String tagTitle;
        @ValueMapValue
        private String description;
        @ValueMapValue
        private String image;
        @ValueMapValue
        private String authorName;
        @ValueMapValue
        private String authorTitle;
        
        public String getTagTitle() {
            return tagTitle;
        }
        public String getDescription() {
            return description;
        }
        public String getImage() {
            return image;
        }
        public String getAuthorName() {
            return authorName;
        }
        public String getAuthorTitle() {
            return authorTitle;
        }
    

        
}
