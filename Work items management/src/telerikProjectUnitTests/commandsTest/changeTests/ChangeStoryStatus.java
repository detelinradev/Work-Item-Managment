package telerikProjectUnitTests.commandsTest.changeTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.change.ChangeStoryStatusCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SizeType;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItemsImpl.StoryImpl;

import java.util.Arrays;

public class ChangeStoryStatus {
    private Team team;
    private Board board;
    private Story story;
    private Engine engine;
    private CreationsFactory factory;

    @Test
    public void ChangeStoryStatusTests_ShouldChangeStatus_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        story = new StoryImpl("kkkk222222kk", "oooooo00000o", StatusTypeStory.DONE, 11,
                PriorityType.HIGH, SizeType.SMALL, board);
        engine.getTeams().add(team);
        engine.getWorkItems().add(story);
        team.getBoardList().add(board);
        board.getWorkItems().add(story);
        ChangeStoryStatusCommand changeStoryStatusCommand = new ChangeStoryStatusCommand(factory, engine);
        changeStoryStatusCommand.execute(Arrays.asList("11","NotDone"));
        Assert.assertEquals("NotDone",story.getStatusType().toString());
    }
}
