package telerikProject.commands.add;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.List;

public class AddCommentStoryCommand extends CommandImpl {

    private WorkItem existingStory;
    private Member existingMember;
    private String comment;

    public AddCommentStoryCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Add Comment to Story command parameters.");
        }
        if (ifSameTeam())
            existingStory.getComments().put(String.format("%s - comment %d"
                    , existingMember.getName(), existingStory.getComments().size() + 1), comment);
        else throw new IllegalArgumentException("Feedback and member does not belong to the same team!");

        writingHistoryFile();

        return String.format("Comment with author %s was added to story with ID %d.",
                existingMember.getName(), existingStory.getId());
    }

    private void writingHistoryFile() {
        existingStory.getHistory().add(String.format("Comment with author %s was added to story with ID %d"
                , existingMember.getName(), existingStory.getId()));
        existingMember.getHistory().add(String.format("Comment with author %s was added to story with ID %d"
                , existingMember.getName(), existingStory.getId()));
    }

    private boolean ifSameTeam() {
        return existingStory.getBoard().getTeamName().equals(existingMember.getTeamName());
    }

    private void parseParameters(List<String> parameters) {
        existingStory = engine.getWorkItems()
                .stream()
                .filter(workItem -> parameters.get(0).equals(Integer.toString(workItem.getId())))
                .findAny().orElseThrow(IllegalArgumentException::new);
        existingMember = engine.getMembers()
                .stream()
                .filter(member -> parameters.get(1).equals(member.getName())).findAny()
                .orElseThrow(IllegalArgumentException::new);
        comment = parameters.get(2);
    }
}
