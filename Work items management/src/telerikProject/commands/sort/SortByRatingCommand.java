package telerikProject.commands.sort;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.Feedback;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByRatingCommand extends CommandImpl {
    public SortByRatingCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        List<Feedback> feedback = engine.getWorkItems()
                .stream()
                .filter(e -> e instanceof Feedback)
                .map(p -> (Feedback) p)
                .sorted(Comparator.comparing(Feedback::getRating))
                .collect(Collectors.toList());

        StringBuilder strBuilder = new StringBuilder();
        for (WorkItem workItem : feedback) {
            strBuilder.append(workItem.toString().trim());
            strBuilder.append(System.lineSeparator());
        }
        return "\n--------------------\nLIST OF WORK ITEMS SORTED BY RATING:\n"
                + strBuilder.toString().trim();
    }
}
