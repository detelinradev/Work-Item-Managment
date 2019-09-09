package telerikProject.core.factories;

import telerikProject.models.enumTypes.*;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItemsImpl.BugImpl;
import telerikProject.models.workItemsImpl.FeedbackImpl;
import telerikProject.models.workItemsImpl.StoryImpl;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;


public class CreationsFactoryImpl implements CreationsFactory {
    private int counter = 10000;

    private int getCounter() {
        return counter;
    }

    public CreationsFactoryImpl() {
    }

    @Override
    public Team createTeam(String name) {
        return new TeamImpl(name);
    }

    @Override
    public Member createMember(String name) {
        return new MemberImpl(name);
    }

    @Override
    public Board createBoard(String name, String teamName) {
        return new BoardImpl(name, teamName);
    }

    public Bug createBug(String title, String description, StatusTypeBug statusTypeBug, int id,
                         PriorityType priorityType, SeverityType severityType, Board boardName) {
        counter++;
        return new BugImpl(title, description, statusTypeBug, getCounter(),
                priorityType, severityType, boardName);
    }

    public Feedback createFeedback(String title, String description, int rating,
                                   StatusTypeFeedback statusTypeFeedback  , int id, Board boardName) {
        counter++;
        return new FeedbackImpl(title, description, rating, statusTypeFeedback, getCounter(), boardName);
    }

    public Story createStory(String title, String description, StatusTypeStory statusTypeStory, int id,
                             PriorityType priorityType, SizeType sizeType, Board boardName) {
        counter++;
        return new StoryImpl(title, description, statusTypeStory, getCounter(),
                priorityType, sizeType, boardName);
    }
}

