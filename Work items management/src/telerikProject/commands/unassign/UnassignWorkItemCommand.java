package telerikProject.commands.unassign;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.WorkItemsWithPriority;

import java.util.List;

public class UnassignWorkItemCommand extends CommandImpl {

    private WorkItemsWithPriority existingWorkItem;
    private Member existingMember;

    public UnassignWorkItemCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Remove Work Item from Person command parameters.");
        }

        listingNewUnAssignment();

        writingHistoryFile();

        return String.format("Work item with ID %d was removed from person %s."
                , existingWorkItem.getId(), existingMember.getName());
    }

    private void listingNewUnAssignment() {
        existingMember.getWorkItems().remove(existingWorkItem);
        existingWorkItem.getAssignees().remove(existingMember);
    }

    private void writingHistoryFile() {
        existingMember.getHistory().add(String.format("Work item with ID %d was removed from person %s"
                , existingWorkItem.getId(), existingMember.getName()));
        existingWorkItem.getHistory().add(String.format("Work item with ID %d was removed from person %s"
                , existingWorkItem.getId(), existingMember.getName()));
    }

    private void parseParameters(List<String> parameters) {
        existingWorkItem = engine.getWorkItems()
                .stream()
                .filter(workItem -> parameters.get(0).equals(Integer.toString(workItem.getId())))
                .map(p -> (WorkItemsWithPriority) p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        existingMember = engine.getMembers()
                .stream()
                .filter(member -> parameters.get(1).equals(member.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
