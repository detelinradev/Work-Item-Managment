package telerikProjectUnitTests.commandsTest.createTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.create.CreateMemberCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.teamImpl.TeamImpl;

import java.util.Arrays;
import java.util.Collections;

public class CreateMemberTests {
    @Test
    public void createMember_ShouldCreateMember_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        engine.getTeams().add(new TeamImpl("TeamName"));
        CreateMemberCommand createMemberCommand = new CreateMemberCommand(factory, engine);
        createMemberCommand.execute(Collections.singletonList("Member"));
        Assert.assertEquals("Member", engine.getMembers().get(0).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createMember_ShouldThrowException_WhenNameAlreadyExists(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        engine.getTeams().add(new TeamImpl("TeamName"));
        Member member = new MemberImpl("Member");
        engine.getMembers().add(member);
        CreateMemberCommand createMemberCommand = new CreateMemberCommand(factory, engine);
        createMemberCommand.execute(Collections.singletonList("Member"));
    }
}
