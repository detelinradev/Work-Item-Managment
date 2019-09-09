package telerikProject.commands.show;


import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;

import java.util.ArrayList;
import java.util.List;

public class ShowMembersCommand extends CommandImpl {

    public ShowMembersCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {

        List<Member> members = engine.getMembers();

        if (members.size() == 0) {
            throw new IllegalArgumentException("There are no registered members.");
        }

        List<String> listMembers = new ArrayList<>();
        for (Member member : members) {
            listMembers.add(" " + member.getName());
        }

        return "\n--------------------\nAll members:\n"
                + String.join("," + System.lineSeparator(), listMembers)
                +"\n--------------------";
    }
}
