package telerikProject.commands.show;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Team;

import java.util.List;
import java.util.stream.Collectors;

public class ShowTeamActivityCommand extends CommandImpl {

    private Team existingTeam;

    public ShowTeamActivityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Show Team Activity command parameters.");
        }

        List<String> listTeamHistory = existingTeam.getMemberList()
                .stream()
                .filter(p -> !p.getHistory().isEmpty())
                .map(member -> member.getHistory().toString())
                .collect(Collectors.toList());
        existingTeam.getBoardList()
                .stream()
                .filter(p -> !p.getHistory().isEmpty())
                .map(board -> board.getHistory().toString())
                .forEach(listTeamHistory::add);

        return String.format("\n--------------------\nTeam: %s\n" +
                        "Activity:\n%s\n--------------------", existingTeam.getName()
                , String.join("," + System.lineSeparator(), listTeamHistory));
    }

    protected void parseParameters(List<String> parameters) {
        existingTeam = engine.getTeams()
                .stream()
                .filter(team -> parameters.get(0).equals(team.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}