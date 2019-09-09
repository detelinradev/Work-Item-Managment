package telerikProject.commands.show;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Team;

import java.util.ArrayList;
import java.util.List;


public class ShowTeamsCommand extends CommandImpl {

    public ShowTeamsCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {
        List<Team> teams = engine.getTeams();

        if (teams.size() == 0) {
            throw new IllegalArgumentException("There are no registered teams.");
        }

        List<String> listTeams = new ArrayList<>();
        for (Team team : teams) {
            listTeams.add(" " + team.getName());
        }

        return "\n--------------------\n" + "All teams:\n"
                + String.join("," + System.lineSeparator(), listTeams)
                +"\n--------------------";
    }
}
