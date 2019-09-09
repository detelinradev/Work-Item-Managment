package telerikProjectUnitTests.commandsTest.addTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.add.AddCommentFeedbackCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItemsImpl.FeedbackImpl;

import java.util.Arrays;

public class AddCommentFeedbackTests {

    private WorkItem feedback;
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
        feedback = new FeedbackImpl("kkkk222222kk", "oooooo00000o", 2,
                StatusTypeFeedback.DONE, 11, board);
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        team.getBoardList().add(board);
        board.getWorkItems().add(feedback);
        engine.getMembers().add(member);
        engine.getWorkItems().add(feedback);
        AddCommentFeedbackCommand addCommentFeedbackCommand = new AddCommentFeedbackCommand(factory, engine);
        addCommentFeedbackCommand.execute(Arrays.asList("11", "nameMember", comment));
        Assert.assertNotNull(feedback.getComments());
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddCommentBugTests_ShouldThrowException_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        member = new MemberImpl("nameMember");
        feedback = new FeedbackImpl("kkkk222222kk", "oooooo00000o", 2,
                StatusTypeFeedback.DONE, 11, board);
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        team.getBoardList().add(board);
        board.getWorkItems().add(feedback);
        engine.getMembers().add(member);
        AddCommentFeedbackCommand addCommentFeedbackCommand = new AddCommentFeedbackCommand(factory, engine);
        addCommentFeedbackCommand.execute(Arrays.asList("11", "nameMember", comment));
    }
}
