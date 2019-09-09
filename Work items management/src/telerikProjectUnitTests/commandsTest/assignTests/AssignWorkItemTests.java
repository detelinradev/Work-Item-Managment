package telerikProjectUnitTests.commandsTest.assignTests;

import org.junit.Test;
import telerikProject.commands.assign.AssignWorkItemCommand;
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

public class AssignWorkItemTests {
    @Test
    public void assignWorkItem_ShouldAssignWorkItem_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItem workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        team.getMemberList().add(member);
        member.setTeamName("telerik");
        board.getWorkItems().add(workItem);
        team.getBoardList().add(board);
        engine.getMembers().add(member);
        engine.getWorkItems().add(workItem);
        AssignWorkItemCommand assignWorkItemCommand = new AssignWorkItemCommand(factory, engine);
        assignWorkItemCommand.execute(Arrays.asList("10001", "memberName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignWorkItem_ShouldThrowException_WhenNotInTheSameTeam(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        Board board = new BoardImpl("boardName", team.getName());
        WorkItem workItem = new BugImpl("imetonabuga", "description",
                StatusTypeBug.ACTIVE, 10001, PriorityType.HIGH, SeverityType.CRITICAL, board);
        team.getMemberList().add(member);
        member.setTeamName("telerik");
        board.getWorkItems().add(workItem);
        team.getBoardList().add(board);
        AssignWorkItemCommand assignWorkItemCommand = new AssignWorkItemCommand(factory, engine);
        assignWorkItemCommand.execute(Arrays.asList("10001", "memberName"));
    }
}
