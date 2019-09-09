package telerikProject.commands.add;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;

import telerikProject.models.team.contracts.Team;

import java.util.List;

public class AddPersonToTeamCommand extends CommandImpl {

    private Member existingMember;
    private Team existingTeam;

    public AddPersonToTeamCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
          parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Add Person to a Team command parameters.");
        }

        existingTeam.getMemberList().add(existingMember);
        existingMember.setTeamName(existingTeam.getName());

        writingHistoryFile();

        return String.format("Person with name %s was added to team %s.",existingMember.getName(),existingTeam.getName() );
    }

    private void writingHistoryFile() {
        existingMember.getHistory().add(String.format("Person with name %s was added to team %s"
                , existingMember.getName(), existingTeam.getName()));
    }

    private void parseParameters(List<String>parameters){
        existingMember =engine.getMembers()
                .stream()
                .filter(member -> parameters.get(0)
                        .equals(member.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        existingTeam = engine.getTeams()
                .stream()
                .filter(team -> parameters.get(1).equals(team.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
