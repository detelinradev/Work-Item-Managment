package telerikProject.core.contracts;


import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.List;

public interface Engine {
    void start();

    List<Member> getMembers();

    List<Team> getTeams();

    List<Board> getBoards();

    List<Integer> getWorkItemIDs();

    List<WorkItem> getWorkItems();

}
