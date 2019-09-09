package telerikProjectUnitTests.commandsTest.changeTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.change.ChangeBugPriorityCommand;
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

public class ChangeBugPriorityTests {
    private Team team;
    private Board board;
    private Bug bug;
    private Engine engine;
    private CreationsFactory factory;

    @Test
    public void ChangeBugPriorityTests_ShouldChangePriority_WhenPassedCorrectParameters() {
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
        ChangeBugPriorityCommand changeBugPriorityCommand = new ChangeBugPriorityCommand(factory, engine);
        changeBugPriorityCommand.execute(Arrays.asList("11","Low"));
        Assert.assertEquals("Low",bug.getPriorityType().toString());
    }

}
