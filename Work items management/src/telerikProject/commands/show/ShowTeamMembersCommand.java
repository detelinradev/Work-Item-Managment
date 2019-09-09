package telerikProject.commands.show;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamMembersCommand extends CommandImpl {

    private Team existingTeam;

    public ShowTeamMembersCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Show Team Members command parameters.");
        }

        if(existingTeam.getMemberList().isEmpty()){
            throw new IllegalArgumentException("There are no registered members in the team.");
        }
        List<Member> listTeamMembers = new ArrayList<>(existingTeam.getMemberList());
        List<String> listMemberNames = new ArrayList<>();
        for(Member member: listTeamMembers){
            listMemberNames.add("  "+ member.getName());
        }

        return String.format("\n--------------------\nTeam: %s\n Members:\n%s\n--------------------"
                , existingTeam.getName(), String.join(","
                        + System.lineSeparator(), listMemberNames));
    }

    private void parseParameters(List<String> parameters) {
        existingTeam = engine.getTeams()
                .stream()
                .filter(team -> parameters.get(0).equals(team.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
