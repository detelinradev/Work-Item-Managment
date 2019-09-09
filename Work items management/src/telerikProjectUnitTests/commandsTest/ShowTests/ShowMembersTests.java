package telerikProjectUnitTests.commandsTest.ShowTests;

import org.junit.Test;
import telerikProject.commands.show.ShowMembersCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.teamImpl.MemberImpl;

import java.util.Arrays;
import java.util.Collections;

public class ShowMembersTests {
    @Test
    public void showMembers_ShouldShowMembers_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberche");
        engine.getMembers().add(member);
        ShowMembersCommand showMembersCommand = new ShowMembersCommand(factory, engine);
        showMembersCommand.execute(Collections.singletonList("memberche"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showMembers_ShouldThrowException_WhenNoMembersInListMembers(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberche");
        ShowMembersCommand showMembersCommand = new ShowMembersCommand(factory, engine);
        showMembersCommand.execute(Collections.singletonList("memberche"));
    }
}
