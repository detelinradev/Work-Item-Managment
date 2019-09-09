package telerikProjectUnitTests.modelsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItemsImpl.BugImpl;


public class WorkitemBaseTests {
    private Board board;
    private WorkItem workItem;
    @Before
    public void InitalizeParameters(){
        board = new BoardImpl("boardname", "telerik");
        workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
    }
    @Test(expected = IllegalArgumentException.class)
    public void createWorkItem_ShouldThrowException_WhenDescriptionLessThanMin() {
        workItem = new BugImpl("imetonabuga", "des",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWorkItem_ShouldThrowException_WhenTitleLessThanMin() {
        workItem = new BugImpl("m", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
    }

    @Test
    public void createWorkItem_ShouldGetTitle_WhenCorrectParsedParameters() {
        Assert.assertNotNull(workItem.getTitle());
    }

    @Test
    public void createWorkItem_ShouldGetDescription_WhenCorrectParsedParameters() {
        Assert.assertNotNull(workItem.getDescription());
    }

    @Test
    public void createWorkItem_ShouldGetComments_WhenCorrectParsedParameters() {
        workItem.getComments().put("Detelin", "komentarche");
        Assert.assertNotNull(workItem.getComments());
    }

    @Test
    public void createWorkItem_ShouldGetHistory_WhenCorrectParsedParameters() {
        workItem.getHistory().add("istoriika");
        Assert.assertNotNull(workItem.getHistory());
    }

    @Test
    public void createWorkItem_ShouldCheckIdNotZero_WhenCorrectParsedParameters() {
        Assert.assertTrue(workItem.getId() != 0);
    }
}
