package telerikProject.commands.sort;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.WorkItem;
import telerikProject.models.workItems.contracts.WorkItemsWithPriority;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByPriorityCommand extends CommandImpl {
    public SortByPriorityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        List<WorkItemsWithPriority> workItems = engine.getWorkItems()
                .stream()
                .filter(e -> e instanceof WorkItemsWithPriority)
                .map(p -> (WorkItemsWithPriority) p)
                .sorted(Comparator.comparing((WorkItemsWithPriority::getPriorityType)))
                .collect(Collectors.toList());

        StringBuilder strBuilder = new StringBuilder();
        for (WorkItem workItem : workItems) {
            strBuilder.append(workItem.toString().trim());
            strBuilder.append(System.lineSeparator());
        }
        return "\n--------------------\nLIST OF WORK ITEMS SORTED BY PRIORITY:\n"
                + strBuilder.toString().trim();
    }
}
