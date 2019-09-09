package telerikProjectUnitTests.commandsTest.filterTests;

import org.junit.Test;
import telerikProject.commands.filter.FilterWorkItemsByStatusCommand;
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
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItemsImpl.BugImpl;

import java.util.Arrays;
import java.util.Collections;

public class FilterWorkItemsByStatusTests {
    @Test
    public void filterWorkItemsByStatus_ShouldFilterWorkItemsByStatus_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        engine.getMembers().add(member);
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        BugImpl workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        workItem.getAssignees().add(member);
        member.getWorkItems().add(workItem);
        engine.getWorkItems().add(workItem);

        FilterWorkItemsByStatusCommand filterWorkItemsByStatusCommand = new FilterWorkItemsByStatusCommand(factory, engine);
        filterWorkItemsByStatusCommand.execute(Collections.singletonList("ACTIVE"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterWorkItemsByStatus_ShouldThrowException_WhenListIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        engine.getMembers().add(member);
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItem workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        FilterWorkItemsByStatusCommand filterWorkItemsByStatusCommand = new FilterWorkItemsByStatusCommand(factory, engine);
        filterWorkItemsByStatusCommand.execute(Collections.singletonList("wrongStatus"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterWorkItemsByStatus_ShouldThrowException_WhenWrongParametersAreParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        engine.getMembers().add(member);
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItem workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        FilterWorkItemsByStatusCommand filterWorkItemsByStatusCommand = new FilterWorkItemsByStatusCommand(factory, engine);
        filterWorkItemsByStatusCommand.execute(Collections.singletonList("FIXED"));
    }
}
