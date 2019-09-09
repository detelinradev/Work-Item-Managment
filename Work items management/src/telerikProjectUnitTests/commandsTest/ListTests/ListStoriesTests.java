package telerikProjectUnitTests.commandsTest.ListTests;

import org.junit.Test;
import telerikProject.commands.list.ListStoriesCommand;
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

public class ListStoriesTests {

    @Test
    public void listBugs(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);

        Story story = new StoryImpl("storytitle", "description",
                StatusTypeStory.DONE, 1, PriorityType.LOW, SizeType.MEDIUM, board);
        engine.getWorkItems().add(story);
        ListStoriesCommand listStoriesCommand = new ListStoriesCommand(factory, engine);
        listStoriesCommand.execute(Collections.singletonList(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void listAllStories_ShouldThrowException_WhenListBugsIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);

        Story story = new StoryImpl("storytitle", "description",
                StatusTypeStory.DONE, 1, PriorityType.LOW, SizeType.MEDIUM, board);
        ListStoriesCommand listStoriesCommand = new ListStoriesCommand(factory, engine);
        listStoriesCommand.execute(Collections.singletonList(""));
    }
}
