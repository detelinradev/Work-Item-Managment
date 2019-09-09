package telerikProject.commands.add;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.List;

public class AddCommentBugCommand extends CommandImpl {

    private WorkItem existingBug;
    private Member existingMember;
    private String comment;

    public AddCommentBugCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Add Comment to Bug command parameters.");
        }

        if (ifSameTeam())
            existingBug.getComments().put(String.format("%s - comment %d"
                    , existingMember.getName(), existingBug.getComments().size() + 1), comment);
        else throw new IllegalArgumentException("Bug and member does not belong to the same team!");

        writingHistoryFile();

        return String.format("Comment with author %s was added to bug with ID %d.",
                existingMember.getName(), existingBug.getId());
    }

    private void writingHistoryFile() {
        existingBug.getHistory().add(String.format("Comment with author %s was added to bug with ID %d"
                , existingMember.getName(), existingBug.getId()));
        existingMember.getHistory().add(String.format("Comment with author %s was added to bug with ID %d"
                , existingMember.getName(), existingBug.getId()));
    }

    private boolean ifSameTeam() {
        return existingBug.getBoard().getTeamName().equals(existingMember.getTeamName());
    }

    private void parseParameters(List<String> parameters) {
        existingBug = engine.getWorkItems()
                .stream()
                .filter(workItem -> parameters.get(0).equals(Integer.toString(workItem.getId())))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        existingMember = engine.getMembers()
                .stream()
                .filter(member -> parameters.get(1).equals(member.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        comment = parameters.get(2);
    }
}
