package telerikProjectUnitTests.modelsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;

public class TeamImplTests {
    private Team team;

    @Before
    public void InitializeParameters(){
        team = new TeamImpl("telerik");
    }

    @Test
    public void createTeam_ShouldGetTeamName_WhenCorrectParsedParameters(){
        team.getName();
        Assert.assertNotNull(team.getName());
    }

    @Test
    public void createTeam_ShouldGetMemberList_WhenCorrectParsedParameters(){
        Member member = new MemberImpl("member");
        team.getMemberList().add(member);
        Assert.assertNotNull(team.getName());
    }

    @Test
    public void createTeam_ShouldGetBoardList_WhenCorrectParsedParameters(){
        Board board = new BoardImpl("member", "telerik");
        team.getBoardList().add(board);
        Assert.assertNotNull(team.getName());
    }
}
