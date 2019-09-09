package telerikProjectUnitTests.commandsTest.ShowTests;

import org.junit.Test;
import telerikProject.commands.show.ShowBoardActivityCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;

import java.util.Arrays;
import java.util.Collections;

public class ShowBoardActivityTests {
    @Test
    public void showBoardActivity_ShouldShowBoardActivity_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("boardName", "telerik");
        engine.getBoards().add(board);
        board.getHistory().add("istoriika malko tuka");
        ShowBoardActivityCommand showBoardActivityCommand = new ShowBoardActivityCommand(factory, engine);
        showBoardActivityCommand.execute(Collections.singletonList("boardName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showBoardActivity_ShouldThrowException_WhenBoardDoesNotExists(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("boardName", "telerik");
        board.getHistory().add("istoriika malko tuka");
        ShowBoardActivityCommand showBoardActivityCommand = new ShowBoardActivityCommand(factory, engine);
        showBoardActivityCommand.execute(Collections.singletonList("boardName"));
    }
}
