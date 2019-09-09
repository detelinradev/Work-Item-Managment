package telerikProjectUnitTests.commandsTest.createTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.create.CreateFeedbackCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;

import java.util.Arrays;

public class CreateFeedbackTests {
    @Test
    public void createFeedback_ShouldCreateFeedback_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        CreateFeedbackCommand createFeedbackCommand = new CreateFeedbackCommand(factory, engine);
        createFeedbackCommand.execute(Arrays.asList("FeedbackTitle", "description", "1", "done", "BoardName"));
        Assert.assertEquals("FeedbackTitle", engine.getWorkItems().get(0).getTitle());
    }

}
