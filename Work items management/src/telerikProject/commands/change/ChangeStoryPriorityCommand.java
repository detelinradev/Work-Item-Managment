package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.workItems.contracts.Story;

import java.util.List;
import java.util.stream.Stream;

public class ChangeStoryPriorityCommand extends CommandImpl {

    private Story existingStory;
    private PriorityType priorityType;

    public ChangeStoryPriorityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Priority of a Story command parameters.");
        }

        existingStory.setPriorityType(priorityType);

        writingHistoryFile();

        return String.format("Priority type for story with ID %d was changed to %s."
                , existingStory.getId(), existingStory.getPriorityType());
    }

    private void writingHistoryFile() {
        existingStory.getHistory().add(String.format("Priority type for story with ID %d was changed to %s"
                , existingStory.getId(), existingStory.getPriorityType()));
    }

    private void parseParameters(List<String>parameters){
        existingStory = engine.getWorkItems()
                .stream()
                .filter(story -> parameters.get(0).equals(Integer.toString(story.getId())))
                .map(p->(Story)p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        priorityType = Stream.of(PriorityType.values())
                .filter(PriorityType -> parameters.get(1).toUpperCase().equals(PriorityType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
