package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.workItems.contracts.Story;

import java.util.List;
import java.util.stream.Stream;

public class ChangeStoryStatusCommand extends CommandImpl {

    private Story existingStory;
    private StatusTypeStory statusTypeStory;

    public ChangeStoryStatusCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Status of a Story command parameters.");
        }

        existingStory.setStatusTypeStory(statusTypeStory);

        writingHistoryFile();

        return String.format("Status type for story with ID %d was changed to %s."
                , existingStory.getId(), existingStory.getStatusType());
    }

    private void writingHistoryFile() {
        existingStory.getHistory().add(String.format("Status type for story with ID %d was changed to %s"
                , existingStory.getId(), existingStory.getStatusType()));
    }

    private void parseParameters(List<String> parameters) {
        existingStory = engine.getWorkItems()
                .stream()
                .filter(story -> parameters.get(0).equals(Integer.toString(story.getId())))
                .map(p -> (Story) p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        statusTypeStory = Stream.of(StatusTypeStory.values())
                .filter(StatusType -> parameters.get(1).toUpperCase().equals(StatusType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
