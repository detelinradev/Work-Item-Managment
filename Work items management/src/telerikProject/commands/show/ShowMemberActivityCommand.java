package telerikProject.commands.show;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;

import java.util.ArrayList;
import java.util.List;

public class ShowMemberActivityCommand extends CommandImpl {

    private Member existingMember;

    public ShowMemberActivityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Show Member Activity command parameters.");
        }
        List<String> listMemberHistory = new ArrayList<>();
        for (String history : existingMember.getHistory()) {
            listMemberHistory.add("  " + history);
        }

        return String.format("\n--------------------\nMember: %s\n Activity:\n%s\n--------------------"
                , existingMember.getName()
                , String.join("," + System.lineSeparator()
                        , listMemberHistory));
    }

    private void parseParameters(List<String> parameters) {
        existingMember = engine.getMembers()
                .stream()
                .filter(member -> parameters.get(0).equals(member.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
