package telerikProjectUnitTests.modelsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItemsImpl.FeedbackImpl;

public class FeedbackImplTests {
    private Board board;
    private Feedback feedback;
    @Before
    public void InitalizeParameters(){
        board = new BoardImpl("boardname", "telerik");
        feedback = new FeedbackImpl("feedbackTitle", "description",
                1, StatusTypeFeedback.NEW, 1, board);
    }
    @Test
    public void createFeedback_ShouldGetRating_WhenCorrectParsedParameters() {
        Assert.assertEquals(1, feedback.getRating());
    }

    @Test
    public void createFeedback_ShouldGetStatus_WhenCorrectParsedParameters() {
        Assert.assertEquals("New", feedback.getStatusType().toString());
    }
}
