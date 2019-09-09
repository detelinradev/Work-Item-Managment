package telerikProject.models.workItems.contracts;

import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.team.contracts.Member;

import java.util.List;

public interface WorkItemsWithPriority extends WorkItem {

    PriorityType getPriorityType();

    void setPriorityType(PriorityType priorityType);

    List<Member> getAssignees();

}
