package telerikProjectUnitTests.modelsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SizeType;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItemsImpl.StoryImpl;

public class StoryImplTests {
    private Board board;
    private Story story;

    @Before
    public void InitalizeParameters(){
        board = new BoardImpl("boardname", "telerik");
        story = new StoryImpl("storytitle", "description",
                StatusTypeStory.DONE, 1, PriorityType.LOW, SizeType.MEDIUM, board);
    }

    @Test
    public void createStory_ShouldGetPriority_WhenCorrectParsedParameters() {
        Assert.assertEquals("Low", story.getPriorityType().toString());
    }
    @Test
    public void createStory_ShouldGetSize_WhenCorrectParsedParameters() {
        Assert.assertEquals("Medium", story.getSizeType().toString());
    }

    @Test
    public void createStory_ShouldGetAssignees_WhenCorrectParsedParameters() {
        Member member = new MemberImpl("membername");
        story.getAssignees().add(member);
        Assert.assertEquals("membername", story.getAssignees().get(0).getName());
    }

    @Test
    public void createStory_ShouldGetStatus_WhenCorrectParsedParameters() {
        Assert.assertEquals("Done", story.getStatusType().toString());
    }
}
