package telerikProjectUnitTests.commandsTest.ListTests;

import org.junit.Test;
import telerikProject.commands.list.ListBugsCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItemsImpl.BugImpl;

import java.util.Arrays;
import java.util.Collections;

public class ListBugsTests {

    @Test
    public void listAllBugs_ShouldListAllBugs_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);

        Bug bug = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
        engine.getWorkItems().add(bug);
        ListBugsCommand listBugsCommand = new ListBugsCommand(factory, engine);
        listBugsCommand.execute(Collections.singletonList(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void listAllWorkItems_ShouldThrowException_WhenListBugsIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);

        Bug bug = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
        ListBugsCommand listBugsCommand = new ListBugsCommand(factory, engine);
        listBugsCommand.execute(Collections.singletonList(""));
    }
}
