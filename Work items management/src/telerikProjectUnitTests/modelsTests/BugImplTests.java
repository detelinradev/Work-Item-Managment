package telerikProjectUnitTests.modelsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItemsImpl.BugImpl;

public class BugImplTests {
    private Board board;
    private Bug bug;

    @Before
    public void InitVariables() {
        board = new BoardImpl("board name", "telerik");
        bug = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
    }

    @Test
    public void createBug_ShouldGetStatus_WhenCorrectParsedParameters() {
        Assert.assertEquals("Active", bug.getStatusType().toString());
    }

    @Test
    public void createBug_ShouldGetStepsToReproduce_WhenCorrectParsedParameters() {
        Assert.assertNotNull(bug.getStepsToReproduce());
    }

    @Test
    public void createBug_ShouldGetPriority_WhenCorrectParsedParameters() {
        Assert.assertEquals("High", bug.getPriorityType().toString());
    }

    @Test
    public void createBug_ShouldGetSeverity_WhenCorrectParsedParameters() {
        Assert.assertEquals("Critical", bug.getSeverityType().toString());
    }

    @Test
    public void createBug_ShouldGetAssignees_WhenCorrectParsedParameters() {
        Member member = new MemberImpl("member");
        bug.getAssignees().add(member);
        Assert.assertEquals("member", bug.getAssignees().get(0).getName());
    }
}
