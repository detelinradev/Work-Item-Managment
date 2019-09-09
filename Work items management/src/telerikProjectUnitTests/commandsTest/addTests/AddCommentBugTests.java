package telerikProjectUnitTests.commandsTest.addTests;

import org.junit.*;
import telerikProject.commands.add.AddCommentBugCommand;
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


public class AddCommentBugTests {
    private WorkItem bug;
    private Member member;
    private String comment;
    private Team team;
    private Board board;
    private Engine engine;
    private CreationsFactory factory;

    @Test
    public void AddCommentBugTests_ShouldAddComment_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        member = new MemberImpl("nameMember");
        bug = new BugImpl("kkkkkkkkkkkk", "oooooo00000o", StatusTypeBug.ACTIVE, 11,
                PriorityType.HIGH, SeverityType.CRITICAL, board);
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        team.getBoardList().add(board);
        board.getWorkItems().add(bug);
        engine.getMembers().add(member);
        engine.getWorkItems().add(bug);
        AddCommentBugCommand addCommentBugCommand = new AddCommentBugCommand(factory, engine);
        addCommentBugCommand.execute(Arrays.asList("11","nameMember",comment));
        Assert.assertNotNull(bug.getComments());
    }
    @Test(expected = IllegalArgumentException.class)
    public void AddCommentBugTests_ShouldThrowException_WhenPassedWrongParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        member = new MemberImpl("nameMember");
        bug = new BugImpl("kkkk222222kk", "oooooo00000o", StatusTypeBug.ACTIVE, 11,
                PriorityType.HIGH, SeverityType.CRITICAL, board);
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        team.getBoardList().add(board);
        board.getWorkItems().add(bug);
        engine.getMembers().add(member);
        AddCommentBugCommand addCommentBugCommand = new AddCommentBugCommand(factory, engine);
        addCommentBugCommand.execute(Arrays.asList("11","nameMember",comment));
    }


}
