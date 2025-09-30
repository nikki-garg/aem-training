package com.aem.training.core.models;

import java.util.Calendar;
import java.util.List;

import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Model(adaptables = Resource.class,resourceType = "aem-training/components/author-cards",defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", options = {
    @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true"),
    @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value="false")
})
public class AuthorCards {


    @ChildResource
    private List<Cards> cards;

    @ValueMapValue
    @Named("jcr:created")
    private Calendar createdAt;

    @ValueMapValue
    @JsonProperty("type")
    @Named("sling:resourceType")
    private String resourceType;


    public String getResourceType() {
        return resourceType;
    }


    public List<Cards> getCards() {
        return cards;
    }

    @JsonProperty(value = "goodbye-world")
    public String goodbyeWorld() {
        return "Goodbye World";
    }

    @JsonIgnore
    public Calendar getCreatedAt() {
        return createdAt;
    }


}
