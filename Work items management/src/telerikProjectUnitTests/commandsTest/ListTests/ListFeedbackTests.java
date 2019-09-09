package telerikProjectUnitTests.commandsTest.ListTests;

import org.junit.Test;
import telerikProject.commands.list.ListFeedbackCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItemsImpl.FeedbackImpl;

import java.util.Arrays;
import java.util.Collections;

public class ListFeedbackTests {
    @Test
    public void listAllFeedback_ShouldListAllFeedback_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);

        Feedback feedback = new FeedbackImpl("feedbackTitle", "description",
                1, StatusTypeFeedback.NEW, 1, board);
        engine.getWorkItems().add(feedback);
        ListFeedbackCommand listFeedbackCommand = new ListFeedbackCommand(factory, engine);
        listFeedbackCommand.execute(Collections.singletonList(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void listAllFeedback_ShouldThrowException_WhenListFeedbackIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        ListFeedbackCommand listFeedbackCommand = new ListFeedbackCommand(factory, engine);
        listFeedbackCommand.execute(Collections.singletonList("feedback"));
    }
}
