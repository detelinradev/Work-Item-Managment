package telerikProjectUnitTests.commandsTest.SortTests;

import org.junit.Test;
import telerikProject.commands.sort.SortByRatingCommand;
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

public class SortByRatingTests {
    @Test
    public void sortByRating_ShouldSortByRating_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("BoardName", "TeamName");
        engine.getBoards().add(board);
        Feedback feedback = new FeedbackImpl("feedbackTitle", "description",
                1, StatusTypeFeedback.NEW, 1, board);
        Feedback feedback2 = new FeedbackImpl("feedbackTitle2", "description",
                2, StatusTypeFeedback.NEW, 1, board);
        engine.getWorkItems().add(feedback);
        engine.getWorkItems().add(feedback2);
        SortByRatingCommand sortByRatingCommand = new SortByRatingCommand(factory,engine);
        sortByRatingCommand.execute(Collections.singletonList(""));
    }
}
