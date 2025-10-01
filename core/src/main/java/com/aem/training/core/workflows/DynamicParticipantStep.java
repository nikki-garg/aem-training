package com.aem.training.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;

@Component(
    service = ParticipantStepChooser.class,
    property = {
        "chooser.label=Dynamic Participant Step"
    }
)
public class DynamicParticipantStep implements ParticipantStepChooser {

    @Override
    public String getParticipant(WorkItem workItem, WorkflowSession session, MetaDataMap metaDataMap) throws WorkflowException {
        // Example logic: Assign based on page path
        String payload = workItem.getWorkflowData().getPayload().toString();

        if (payload.contains("/content/aem-training/en/marketing")) {
            return "marketing-approver-group";  // Group ID in AEM
        } else {
            return "default-approver-group";
        }
    }
}
