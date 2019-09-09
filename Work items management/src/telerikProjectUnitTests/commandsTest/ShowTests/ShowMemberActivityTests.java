package telerikProjectUnitTests.commandsTest.ShowTests;

import org.junit.Test;
import telerikProject.commands.show.ShowMemberActivityCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.teamImpl.MemberImpl;

import java.util.Arrays;
import java.util.Collections;

public class ShowMemberActivityTests {
    @Test
    public void showMemberActivity_ShouldShowMemberActivity_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        engine.getMembers().add(member);
        member.getHistory().add("istoriika malko tuka");
        ShowMemberActivityCommand showMemberActivityCommand = new ShowMemberActivityCommand(factory, engine);
        showMemberActivityCommand.execute(Collections.singletonList("memberName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showMemberActivity_ShouldThrowException_WhenMemberIsNotAddedInList(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Member member = new MemberImpl("memberName");
        member.getHistory().add("istoriika malko tuka");
        ShowMemberActivityCommand showMemberActivityCommand = new ShowMemberActivityCommand(factory, engine);
        showMemberActivityCommand.execute(Collections.singletonList("memberName"));
    }
}
