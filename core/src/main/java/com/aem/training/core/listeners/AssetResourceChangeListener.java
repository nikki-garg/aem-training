package com.aem.training.core.listeners;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component(service = ResourceChangeListener.class,
        immediate = true,
        property = {
            ResourceChangeListener.PATHS+"=/content/dam/mysite/resource",
            ResourceChangeListener.CHANGES +"=ADDED",
            ResourceChangeListener.CHANGES +"=CHANGED",
            ResourceChangeListener.CHANGES +"=REMOVED"
        })

@ServiceDescription("Demo to listen on changes in the resource tree")

public class AssetResourceChangeListener implements ResourceChangeListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onChange(List<ResourceChange> list) {
        for (ResourceChange resourceChange : list) {
            logger.debug("Resource event: {} at: {}", resourceChange.getType(), resourceChange.getPath());
        }

    }
}
