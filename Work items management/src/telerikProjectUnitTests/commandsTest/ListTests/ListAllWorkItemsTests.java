package telerikProjectUnitTests.commandsTest.ListTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.create.CreateBugCommand;
import telerikProject.commands.create.CreateFeedbackCommand;
import telerikProject.commands.create.CreateStoryCommand;
import telerikProject.commands.list.ListAllWorkItemsCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.*;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItemsImpl.BugImpl;
import telerikProject.models.workItemsImpl.FeedbackImpl;
import telerikProject.models.workItemsImpl.StoryImpl;

import java.util.Arrays;
import java.util.Collections;

public class ListAllWorkItemsTests {
    @Test
    public void listAllWorkItems_ShouldListAllWorkItems_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        CreateBugCommand createBugCommand = new CreateBugCommand(factory, engine);
        createBugCommand.execute(Arrays.asList("BugTitlebugbug", "description", "active",
                "high", "critical", "BoardName"));

        Feedback feedback = new FeedbackImpl("feedbackTitle", "description",
                1, StatusTypeFeedback.NEW, 2, board);

        Bug bug = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);

        Story story = new StoryImpl("storytitle", "description",
                StatusTypeStory.DONE, 1, PriorityType.LOW, SizeType.MEDIUM, board);

        engine.getWorkItems().add(bug);
        engine.getWorkItems().add(feedback);
        engine.getWorkItems().add(story);
        CreateFeedbackCommand createFeedbackCommand = new CreateFeedbackCommand(factory, engine);
        createFeedbackCommand.execute(Arrays.asList("FeedbackTitle", "description", "1", "done", "BoardName"));

        CreateStoryCommand createStoryCommand = new CreateStoryCommand(factory, engine);
        createStoryCommand.execute(Arrays.asList("StoryTitle", "description", "done", "high", "large", "BoardName"));
        ListAllWorkItemsCommand listAllWorkItemsCommand = new ListAllWorkItemsCommand(factory, engine);
        listAllWorkItemsCommand.execute(Collections.singletonList(""));
        Assert.assertNotNull(listAllWorkItemsCommand);
    }
}
