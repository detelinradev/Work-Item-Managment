package telerikProject.commands.sort;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBySeverityCommand extends CommandImpl {
    public SortBySeverityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        List<Bug> workItems = engine.getWorkItems()
                .stream()
                .filter(e -> e instanceof Bug)
                .map(p -> (Bug) p)
                .sorted(Comparator.comparing(Bug::getSeverityType))
                .collect(Collectors.toList());

        StringBuilder strBuilder = new StringBuilder();
        for (WorkItem workItem : workItems) {
            strBuilder.append(workItem.toString().trim());
            strBuilder.append(System.lineSeparator());
        }
        return "\n--------------------\nLIST OF WORK ITEMS SORTED BY SEVERITY:\n"
                + strBuilder.toString().trim();
    }
}
