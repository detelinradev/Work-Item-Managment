package telerikProject.commands.filter;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.StatusType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItems.contracts.WorkItemsWithPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterWorkItemsByStatusAndAssigneeCommand extends CommandImpl {

    private StatusType statusType;
    private Member assigneeNew;

    public FilterWorkItemsByStatusAndAssigneeCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse List WorkItems By Status And Assignee command parameters.");
        }

        List<WorkItemsWithPriority> workItemsWithStatusAndAssignee = engine.getWorkItems()
                .stream()
                .filter(e -> e.getStatusType().equals(statusType))
                .filter(e -> !e.getClass().getSimpleName().toLowerCase().equals("feedbackimpl"))
                .map(p -> (WorkItemsWithPriority) p)
                .filter(e -> e.getAssignees().contains(assigneeNew))
                .collect(Collectors.toList());

        if (workItemsWithStatusAndAssignee.size() == 0) {
            throw new IllegalArgumentException("There are no registered assignees or status.");
        }
        List<String> listWorkItemsWithMemberAndAssignee = new ArrayList<>();
        for (WorkItem workItem : workItemsWithStatusAndAssignee) {
            listWorkItemsWithMemberAndAssignee.add(workItem.toString());
        }
        return String.format("\n--------------------\nWORK ITEMS WITH STATUS %s AND ASSIGNEE %s\n"
                , statusType, assigneeNew.getName())
                + String.join("", listWorkItemsWithMemberAndAssignee).trim();

    }

    private void parseParameters(List<String> parameters) {
        try {
            statusType = Stream.of(StatusTypeBug.values())
                    .filter(StatusType -> parameters.get(0).toUpperCase().equals(StatusType.name()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
            statusType = Stream.of(StatusTypeStory.values())
                    .filter(StatusType -> parameters.get(0).toUpperCase().equals(StatusType.name()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
            statusType = Stream.of(StatusTypeFeedback.values())
                    .filter(StatusType -> parameters.get(0).toUpperCase().equals(StatusType.name()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }catch (Exception ignored){}
        if(statusType == null) throw new IllegalArgumentException();
        assigneeNew = engine.getMembers()
                .stream()
                .filter(member -> parameters.get(1).equals(member.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
