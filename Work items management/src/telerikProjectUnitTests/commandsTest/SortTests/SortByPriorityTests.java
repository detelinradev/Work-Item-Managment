package telerikProjectUnitTests.commandsTest.SortTests;

import org.junit.Test;
import telerikProject.commands.sort.SortByPriorityCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.*;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItemsImpl.BugImpl;
import telerikProject.models.workItemsImpl.StoryImpl;

import java.util.Arrays;
import java.util.Collections;

public class SortByPriorityTests {
    @Test
    public void sortByPriority_ShouldSortByPriority_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        Bug bug = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
        Story story = new StoryImpl("storytitle", "description",
                StatusTypeStory.DONE, 1, PriorityType.LOW, SizeType.MEDIUM, board);
        engine.getWorkItems().add(bug);
        engine.getWorkItems().add(story);
        SortByPriorityCommand sortByPriorityCommand = new SortByPriorityCommand(factory,engine);
        sortByPriorityCommand.execute(Collections.singletonList(""));
    }
}
