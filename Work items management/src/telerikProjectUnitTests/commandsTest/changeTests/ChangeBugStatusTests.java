package telerikProjectUnitTests.commandsTest.changeTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.change.ChangeBugStatusCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItemsImpl.BugImpl;

import java.util.Arrays;

public class ChangeBugStatusTests {
    private Team team;
    private Board board;
    private Bug bug;
    private Engine engine;
    private CreationsFactory factory;

    @Test
    public void ChangeBugStatusTests_ShouldChangeStatus_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        bug = new BugImpl("kkkk222222kk", "oooooo00000o", StatusTypeBug.ACTIVE, 11,
                PriorityType.HIGH, SeverityType.CRITICAL, board);
        engine.getTeams().add(team);
        engine.getWorkItems().add(bug);
        team.getBoardList().add(board);
        board.getWorkItems().add(bug);
        ChangeBugStatusCommand changeBugStatusCommand = new ChangeBugStatusCommand(factory, engine);
        changeBugStatusCommand.execute(Arrays.asList("11","Fixed"));
        Assert.assertEquals("Fixed",bug.getStatusType().toString());
    }
}
