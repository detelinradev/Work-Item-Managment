package telerikProjectUnitTests.commandsTest.filterTests;

import org.junit.Test;
import telerikProject.commands.filter.FilterWorkItemsByAssigneeCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.WorkItemsWithPriority;
import telerikProject.models.workItemsImpl.BugImpl;

import java.util.Arrays;
import java.util.Collections;

public class FilterWorkItemsByAssigneeTests {
    @Test
    public void filterWorkItemsByAssignee_ShouldFilterWorkItemsByAssignee_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItemsWithPriority workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        engine.getWorkItems().add(workItem);
        engine.getMembers().add(member);
        workItem.getAssignees().add(member);
        member.getWorkItems().add(workItem);
        FilterWorkItemsByAssigneeCommand filterWorkItemsByAssigneeCommand = new FilterWorkItemsByAssigneeCommand(factory, engine);
        filterWorkItemsByAssigneeCommand.execute(Collections.singletonList("memberName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterWorkItemsByAssignee_ShouldThrowException_WhenWrongParametersAreParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItemsWithPriority workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        engine.getWorkItems().add(workItem);
        engine.getMembers().add(member);
        workItem.getAssignees().add(member);
        member.getWorkItems().add(workItem);
        FilterWorkItemsByAssigneeCommand filterWorkItemsByAssigneeCommand = new FilterWorkItemsByAssigneeCommand(factory, engine);
        filterWorkItemsByAssigneeCommand.execute(Collections.singletonList("memb"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterWorkItemsByAssignee_ShouldThrowException_WhenListIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItemsWithPriority workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        FilterWorkItemsByAssigneeCommand filterWorkItemsByAssigneeCommand = new FilterWorkItemsByAssigneeCommand(factory, engine);
        filterWorkItemsByAssigneeCommand.execute(Collections.singletonList("memberName"));
    }
}
