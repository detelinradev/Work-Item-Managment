package telerikProject.commands.assign;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItems.contracts.WorkItemsWithPriority;

import java.util.List;

public class AssignWorkItemCommand extends CommandImpl {

    private WorkItemsWithPriority existingWorkItem;
    private Member existingMember;

    public AssignWorkItemCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Add Work Item to Person command parameters.");
        }

        if (ifBelongSameTeam(existingMember, existingWorkItem)) {
            listingNewAssignment();
        } else throw new IllegalArgumentException("Member and work item does not belong to the same team");

        writingHistoryFile();

        return String.format("Work item with ID %d was added to person %s."
                , existingWorkItem.getId(), existingMember.getName());
    }

    private void listingNewAssignment() {
        existingMember.getWorkItems().add(existingWorkItem);
        existingWorkItem.getAssignees().add(existingMember);
    }

    private void writingHistoryFile() {
        existingMember.getHistory().add(String.format("Work item with ID %d was added to person %s"
                , existingWorkItem.getId(), existingMember.getName()));
        existingWorkItem.getHistory().add(String.format("Work item with ID %d was assigned to person %s"
                , existingWorkItem.getId(), existingMember.getName()));
    }

    private boolean ifBelongSameTeam(Member existingMember, WorkItem existingWorkItem) {
        return existingWorkItem.getBoard().getTeamName().equals(existingMember.getTeamName());
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
