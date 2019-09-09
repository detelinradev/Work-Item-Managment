package telerikProjectUnitTests.commandsTest.createTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.create.CreateBoardCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.teamImpl.TeamImpl;

import java.util.Arrays;

public class CreateBoardTests {
    @Test
    public void createBoard_ShouldCreateBoard_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        engine.getTeams().add(new TeamImpl("TeamName"));
        CreateBoardCommand createBoardCommand = new CreateBoardCommand(factory, engine);
        createBoardCommand.execute(Arrays.asList("TeamName", "Board"));
        Assert.assertEquals("Board", engine.getBoards().get(0).getName());
    }
}
