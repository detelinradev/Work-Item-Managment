package telerikProjectUnitTests.commandsTest.createTests;

import org.junit.Assert;
import org.junit.Test;
import telerikProject.commands.create.CreateTeamCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.teamImpl.TeamImpl;

import java.util.Arrays;
import java.util.Collections;

public class CreateTeamTests {

    @Test
    public void createTeam_ShouldCreateTeam_WhenCorrectParametersParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        engine.getTeams().add(new TeamImpl("TeamName"));
        CreateTeamCommand createTeamCommand = new CreateTeamCommand(factory, engine);
        createTeamCommand.execute(Collections.singletonList("TeamName"));
        Assert.assertEquals("TeamName", engine.getTeams().get(0).getName());
    }
}
