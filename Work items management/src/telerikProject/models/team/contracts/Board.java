package telerikProject.models.team.contracts;

import telerikProject.models.workItems.contracts.WorkItem;

import java.util.List;

public interface Board{
    String getName();

    List<WorkItem> getWorkItems();

    List<String> getHistory();

//    void addWorkItem (WorkItem workItem);
//
//    void removeWorkItem(WorkItem workItem);
//
//    void addHistory (String string);

    String getTeamName();
}
