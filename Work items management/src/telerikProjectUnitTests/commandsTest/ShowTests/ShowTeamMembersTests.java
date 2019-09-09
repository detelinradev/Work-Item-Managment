package telerikProjectUnitTests.commandsTest.ShowTests;

import org.junit.Test;
import telerikProject.commands.show.ShowTeamMembersCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;

import java.util.Arrays;
import java.util.Collections;

public class ShowTeamMembersTests {
    @Test
    public void showTeamMembers_ShouldShowTeamMembers_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        team.getMemberList().add(member);
        ShowTeamMembersCommand showTeamMembersCommand = new ShowTeamMembersCommand(factory,engine);
        showTeamMembersCommand.execute(Collections.singletonList("telerik"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showTeamMembers_ShouldThrowException_WhenListIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        ShowTeamMembersCommand showTeamMembersCommand = new ShowTeamMembersCommand(factory,engine);
        showTeamMembersCommand.execute(Collections.singletonList("telerik"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showTeamMembers_ShouldThrowExcepton_WhenWrongParametersAreParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        team.getMemberList().add(member);
        ShowTeamMembersCommand showTeamMembersCommand = new ShowTeamMembersCommand(factory,engine);
        showTeamMembersCommand.execute(Collections.singletonList("teleriken"));
    }
}
