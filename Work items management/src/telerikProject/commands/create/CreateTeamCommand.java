package telerikProject.commands.create;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Team;

import java.util.List;

public class CreateTeamCommand extends CommandImpl {

    private String name;

    public CreateTeamCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Create Team command parameters.");
        }

        Team team = factory.createTeam(name);

        listingNewStory(team);

        return String.format("Team with name %s was created.", name);
    }

    private void listingNewStory(Team team) {
        engine.getTeams().add(team);
    }

    private void parseParameters(List<String> parameters) {
        name = parameters.get(0);
    }
}
