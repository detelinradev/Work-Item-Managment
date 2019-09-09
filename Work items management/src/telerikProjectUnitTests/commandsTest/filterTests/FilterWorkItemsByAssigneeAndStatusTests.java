package telerikProjectUnitTests.commandsTest.filterTests;

import org.junit.Test;
import telerikProject.commands.filter.FilterWorkItemsByStatusAndAssigneeCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItems.contracts.WorkItemsWithPriority;
import telerikProject.models.workItemsImpl.BugImpl;
import telerikProject.models.workItemsImpl.FeedbackImpl;

import java.util.Arrays;

public class FilterWorkItemsByAssigneeAndStatusTests {
    @Test
    public void filterWorkItemsByAssigneeAndStatus_ShouldFilterWorkItemsByAssigneeAndStatus_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        engine.getMembers().add(member);
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItemsWithPriority workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        workItem.getAssignees().add(member);
        engine.getWorkItems().add(workItem);
        FilterWorkItemsByStatusAndAssigneeCommand filterWorkItemsByStatusAndAssigneeCommand = new FilterWorkItemsByStatusAndAssigneeCommand(factory, engine);
        filterWorkItemsByStatusAndAssigneeCommand.execute(Arrays.asList("ACTIVE", "memberName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterWorkItemsByAssigneeAndStatus_ShouldThrowException_WhenListIsempty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        engine.getMembers().add(member);
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", "telerik");
        WorkItemsWithPriority workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        FilterWorkItemsByStatusAndAssigneeCommand filterWorkItemsByStatusAndAssigneeCommand = new FilterWorkItemsByStatusAndAssigneeCommand(factory, engine);
        filterWorkItemsByStatusAndAssigneeCommand.execute(Arrays.asList("ACTIVE", "memberName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterWorkItemsByAssigneeAndStatus_ShouldThrowException_WhenWrongParametersAreParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        Feedback workItem = new FeedbackImpl("feedbackTitle", "description",
                1, StatusTypeFeedback.NEW, 1, board);
        FilterWorkItemsByStatusAndAssigneeCommand filterWorkItemsByStatusAndAssigneeCommand = new FilterWorkItemsByStatusAndAssigneeCommand(factory, engine);
        filterWorkItemsByStatusAndAssigneeCommand.execute(Arrays.asList("ACTIVE", "memberName"));
    }
}
