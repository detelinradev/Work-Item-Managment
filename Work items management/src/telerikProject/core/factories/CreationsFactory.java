package telerikProject.core.factories;


import telerikProject.models.enumTypes.*;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItems.contracts.Story;

public interface CreationsFactory {


    Team createTeam(String name);

    Member createMember(String name);

    Board createBoard(String name, String teamName);

    Bug createBug(String title, String description, StatusTypeBug statusTypeBug, int id, PriorityType priorityType,
                  SeverityType severityType, Board boardName);

    Feedback createFeedback(String title, String description, int rating,
                            StatusTypeFeedback statusTypeFeedback, int id, Board boardName);

    Story createStory(String title, String description, StatusTypeStory statusTypeStory, int id,
                      PriorityType priorityType, SizeType sizeType, Board boardName);


}
