package telerikProject.commands.list;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.Story;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListStoriesCommand extends CommandImpl {
    public ListStoriesCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {
        List<Story> stories = engine.getWorkItems()
                .stream()
                .filter(p -> p instanceof Story)
                .map(k -> (Story) k)
                .collect(Collectors.toList());

        if (stories.size() == 0) {
            throw new IllegalArgumentException("There are no registered stories.");
        }

        List<String> listStories = new ArrayList<>();
        for (Story story : stories) {
            listStories.add(story.toString());
        }
        if(listStories.isEmpty())
            listStories.add("There are no registered stories.");

        return "\n--------------------\nLIST OF ALL STORIES:\n"
                + String.join("" , listStories).trim();
    }

}
