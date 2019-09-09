package telerikProject.commands.create;


import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;

import java.util.List;

public class CreateMemberCommand extends CommandImpl {

    private String name;

    public CreateMemberCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        Member member;

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Create Person command parameters.");
        }

        if (checkUniqueName(parameters)) {
            member = factory.createMember(name);
        } else
            throw new IllegalArgumentException(String.format("Member with name %s already exist!", name));

        listingNewMember(member);

        return String.format("Member with name %s was created.", member.getName());
    }

    private void listingNewMember(Member member) {
        engine.getMembers().add(member);
    }

    private boolean checkUniqueName(List<String> parameters) {
        return engine.getMembers()
                .stream()
                .noneMatch(member -> member.getName().equals(parameters.get(0)));
    }

    private void parseParameters(List<String> parameters) {
        name = parameters.get(0);
    }
}
