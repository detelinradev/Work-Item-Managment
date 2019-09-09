package telerikProjectUnitTests.commandsTest.addTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.add.AddPersonToTeamCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;

import java.util.Arrays;

public class AddPersonToTeamTests {
    private Member member;
    private Team team;
    private Engine engine;
    private CreationsFactory factory;

    @Test
    public void AddPersonToTeamTests_ShouldAddPerson_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        member = new MemberImpl("nameMember");
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        engine.getMembers().add(member);
        engine.getTeams().add(team);
        AddPersonToTeamCommand addPersonToTeamCommand = new AddPersonToTeamCommand(factory, engine);
        addPersonToTeamCommand.execute(Arrays.asList("nameMember","Team"));
        Assert.assertNotNull(team.getMemberList());
    }
    @Test(expected = IllegalArgumentException.class)
    public void AddPersonToTeamTests_ShouldThrowException_WhenPassedCorrectParameters() {
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        team = new TeamImpl("Team");
        member = new MemberImpl("nameMember");
        team.getMemberList().add(member);
        member.setTeamName(team.getName());
        engine.getMembers().add(member);
        AddPersonToTeamCommand addPersonToTeamCommand = new AddPersonToTeamCommand(factory, engine);
        addPersonToTeamCommand.execute(Arrays.asList("nameMember","Team"));
    }
}
