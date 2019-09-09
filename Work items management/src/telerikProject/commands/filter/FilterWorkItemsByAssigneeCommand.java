package telerikProject.commands.filter;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItems.contracts.WorkItemsWithPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterWorkItemsByAssigneeCommand extends CommandImpl {

    private Member assignee;

    public FilterWorkItemsByAssigneeCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse List WorkItems By Assignee command parameters.");
        }

        List<WorkItemsWithPriority> workItemsWithAssignee = engine.getWorkItems()
                .stream()
                .filter(e -> !e.getClass().getSimpleName().toLowerCase().equals("feedbackimpl"))
                .map(p -> (WorkItemsWithPriority) p)
                .filter(e -> e.getAssignees().contains(assignee))
                .collect(Collectors.toList());

        if (workItemsWithAssignee.size() == 0) {
            throw new IllegalArgumentException("There are no registered assignees.");
        }

        List<String> listWorkItemsWithAssignee = new ArrayList<>();
        for (WorkItem workItem : workItemsWithAssignee) {
            listWorkItemsWithAssignee.add(workItem.toString());
        }

        return String.format("\n--------------------\nWORK ITEMS ASSIGNED TO %s:\n"
                , assignee.getName())
                + String.join("", listWorkItemsWithAssignee).trim();
    }

    private void parseParameters(List<String> parameters) {
        assignee = engine.getMembers()
                .stream()
                .filter(member -> parameters.get(0).equals(member.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
