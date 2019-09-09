package telerikProjectUnitTests.commandsTest.createTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.create.CreateBugCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;

import java.util.Arrays;

public class CreateBugTests {
    @Test
    public void createBug_ShouldCreateBug_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        CreateBugCommand createBugCommand = new CreateBugCommand(factory, engine);
        createBugCommand.execute(Arrays.asList("BugTitlebugbug", "description", "active", "high", "critical", "BoardName"));
        Assert.assertEquals("BugTitlebugbug", engine.getWorkItems().get(0).getTitle());
    }
}
