package telerikProject.models.workItems.contracts;

import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;

import java.util.List;

public interface Bug extends WorkItemsWithPriority {

    List<String> getStepsToReproduce();

    SeverityType getSeverityType();

    void setSeverityType(SeverityType severityType);

    void setStatusTypeBug (StatusTypeBug statusTypeBug);
    
}
