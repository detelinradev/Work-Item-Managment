package telerikProjectUnitTests.commandsTest.changeTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.change.ChangeFeedbackStatusCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItemsImpl.FeedbackImpl;

import java.util.Arrays;

public class ChangeFeedbackStatusTests {
    private Team team;
    private Board board;
    private Feedback feedback;
    private Engine engine;
    private CreationsFactory factory;

    @Test
    public void ChangeFeedbackStatusTests_ShouldChangeStatus_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        feedback = new FeedbackImpl("kkkk222222kk", "oooooo00000o",1, StatusTypeFeedback.DONE, 11,
                board);
        engine.getTeams().add(team);
        engine.getWorkItems().add(feedback);
        team.getBoardList().add(board);
        board.getWorkItems().add(feedback);
        ChangeFeedbackStatusCommand changeFeedbackStatusCommand = new ChangeFeedbackStatusCommand(factory, engine);
        changeFeedbackStatusCommand.execute(Arrays.asList("11","New"));
        Assert.assertEquals("New",feedback.getStatusType().toString());
    }
}
