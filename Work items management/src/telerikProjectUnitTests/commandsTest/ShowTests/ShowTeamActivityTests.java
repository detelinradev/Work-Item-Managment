package telerikProjectUnitTests.commandsTest.ShowTests;

import org.junit.Test;
import telerikProject.commands.show.ShowTeamActivityCommand;
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

public class ShowTeamActivityTests {
    @Test
    public void showTeamActivity_ShouldShowTeamActivity_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        Member member = new MemberImpl("memberName");
        team.getMemberList().add(member);
        member.getHistory().add("istoriika malko tuka");
        ShowTeamActivityCommand showTeamActivityCommand = new ShowTeamActivityCommand(factory, engine);
        showTeamActivityCommand.execute(Collections.singletonList("telerik"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showTeamActivity_ShouldThrowException_WhenTeamIsNotAddedInListTeams(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        Member member = new MemberImpl("memberName");
        team.getMemberList().add(member);
        member.getHistory().add("istoriika malko tuka");
        ShowTeamActivityCommand showTeamActivityCommand = new ShowTeamActivityCommand(factory, engine);
        showTeamActivityCommand.execute(Collections.singletonList("telerikchence"));
    }
}
