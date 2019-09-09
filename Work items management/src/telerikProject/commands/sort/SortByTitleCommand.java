package telerikProject.commands.sort;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByTitleCommand extends CommandImpl {
    public SortByTitleCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        List<WorkItem> workItems = engine.getWorkItems()
                .stream()
                .filter(e -> !e.getTitle().isEmpty())
                .sorted(Comparator.comparing(WorkItem::getTitle))
                .collect(Collectors.toList());

        StringBuilder strBuilder = new StringBuilder();
        for (WorkItem workItem : workItems) {
            strBuilder.append(workItem.toString().trim());
            strBuilder.append(System.lineSeparator());
        }
        return "\n--------------------\nLIST OF WORK ITEMS SORTED BY TITLE:\n"
                + strBuilder.toString().trim();
    }
}
