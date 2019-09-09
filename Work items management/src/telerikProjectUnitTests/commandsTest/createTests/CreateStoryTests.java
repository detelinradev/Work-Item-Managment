package telerikProjectUnitTests.commandsTest.createTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.create.CreateStoryCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;

import java.util.Arrays;

public class CreateStoryTests {
    @Test
    public void createStory_ShouldCreateStory_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        CreateStoryCommand createStoryCommand = new CreateStoryCommand(factory, engine);
        createStoryCommand.execute(Arrays.asList("StoryTitle", "description", "done", "high", "large", "BoardName"));
        Assert.assertEquals("StoryTitle", engine.getWorkItems().get(0).getTitle());
    }
}
