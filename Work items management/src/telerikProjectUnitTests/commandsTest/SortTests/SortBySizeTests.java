package telerikProjectUnitTests.commandsTest.SortTests;

import org.junit.Test;
import telerikProject.commands.sort.SortBySizeCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.*;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItemsImpl.StoryImpl;

import java.util.Arrays;
import java.util.Collections;

public class SortBySizeTests {
    @Test
    public void sortBySize_ShouldSortBySize_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        Story story = new StoryImpl("storytitle", "description",
                StatusTypeStory.DONE, 1, PriorityType.LOW, SizeType.MEDIUM, board);
        Story story2 = new StoryImpl("storytitle2", "description",
                StatusTypeStory.DONE, 2, PriorityType.LOW, SizeType.MEDIUM, board);
        engine.getWorkItems().add(story);
        engine.getWorkItems().add(story2);
        SortBySizeCommand sortBySizeCommand = new SortBySizeCommand(factory,engine);
        sortBySizeCommand.execute(Collections.singletonList(""));
    }
}
