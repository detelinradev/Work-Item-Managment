package telerikProject.models.workItems.contracts;


import telerikProject.models.enumTypes.StatusType;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.team.contracts.Board;

import java.util.List;
import java.util.Map;

public interface WorkItem {

    int getId();

    String getTitle();

    String getDescription();

    StatusType getStatusType();

    Map<String,String> getComments();

    List<String> getHistory();

    Board getBoard();

}
