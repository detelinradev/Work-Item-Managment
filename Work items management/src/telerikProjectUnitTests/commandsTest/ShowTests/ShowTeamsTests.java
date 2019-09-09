package telerikProjectUnitTests.commandsTest.ShowTests;

import org.junit.Test;
import telerikProject.commands.show.ShowTeamsCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.TeamImpl;

import java.util.Arrays;
import java.util.Collections;

public class ShowTeamsTests {
    @Test
    public void showTeams_ShouldShowTeams_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        ShowTeamsCommand showTeamsCommand = new ShowTeamsCommand(factory,engine);
        showTeamsCommand.execute(Collections.singletonList(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showTeams_ShouldThrowException_WhenListIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        ShowTeamsCommand showTeamsCommand = new ShowTeamsCommand(factory,engine);
        showTeamsCommand.execute(Collections.singletonList(""));
    }
}
