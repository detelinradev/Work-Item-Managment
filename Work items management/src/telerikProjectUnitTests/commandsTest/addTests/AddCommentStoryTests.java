package telerikProjectUnitTests.commandsTest.addTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.add.AddCommentStoryCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.*;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItemsImpl.StoryImpl;

import java.util.Arrays;

public class AddCommentStoryTests {

    private WorkItem story;
    private Member member;
    private String comment;
    private Team team;
    private Board board;
    private Engine engine;
    private CreationsFactory factory;

    @Test
    public void AddCommentStoryTests_ShouldAddComment_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        member = new MemberImpl("nameMember");
        story = new StoryImpl("kkkk222222kk", "oooooo00000o", StatusTypeStory.DONE, 11,
                PriorityType.HIGH, SizeType.LARGE, board);
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        team.getBoardList().add(board);
        board.getWorkItems().add(story);
        engine.getMembers().add(member);
        engine.getWorkItems().add(story);
        AddCommentStoryCommand addCommentStoryCommand = new AddCommentStoryCommand(factory, engine);
        addCommentStoryCommand.execute(Arrays.asList("11","nameMember",comment));
        Assert.assertNotNull(story.getComments());
    }
    @Test(expected = IllegalArgumentException.class)
    public void AddCommentStoryTests_ShouldThrowException_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        board = new BoardImpl("board", "Team");
        member = new MemberImpl("nameMember");
        story = new StoryImpl("kkkk222222kk", "oooooo00000o", StatusTypeStory.DONE, 11,
                PriorityType.HIGH, SizeType.LARGE, board);
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        team.getBoardList().add(board);
        board.getWorkItems().add(story);
        engine.getMembers().add(member);
        AddCommentStoryCommand addCommentStoryCommand = new AddCommentStoryCommand(factory, engine);
        addCommentStoryCommand.execute(Arrays.asList("11","nameMember",comment));
    }

}
