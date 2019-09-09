package telerikProject.commands.sort;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.Story;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBySizeCommand extends CommandImpl {
    public SortBySizeCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        List<Story> workItems = engine.getWorkItems()
                .stream()
                .filter(e -> e instanceof Story)
                .map(p -> (Story) p)
                .sorted(Comparator.comparing(Story::getSizeType))
                .collect(Collectors.toList());

        StringBuilder strBuilder = new StringBuilder();
        for (WorkItem workItem : workItems) {
            strBuilder.append(workItem.toString().trim());
            strBuilder.append(System.lineSeparator());
        }
        return "\n--------------------\nLIST OF WORK ITEMS SORTED BY SIZE:\n"
                + strBuilder.toString().trim();
    }
}
