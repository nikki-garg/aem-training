package com.aem.training.core.workflows;


import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    service = WorkflowProcess.class,
    property = {
        "process.label=Custom Workflow Process Step"
    }
)
public class CustomProcessStep implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(CustomProcessStep.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args)
            throws WorkflowException {

        // Get payload (the resource or page on which workflow is running)
        String payload = workItem.getWorkflowData().getPayload().toString();

        // Log or perform custom logic
        LOG.debug("Executing custom workflow process on payload: {}" , payload);

        // Example: Add metadata
        workItem.getWorkflowData().getMetaDataMap().put("processedBy", "CustomProcessStep");
    }

    
}

