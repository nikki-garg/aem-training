package com.aem.training.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,resourceType = "aem-training/components/author-cards",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json")
public class AuthorCards {


    @ChildResource
    private List<Cards> cards;

    public List<Cards> getCards() {
        return cards;
    }

    public String getTitle() {
        return "My Title";
    }
}
