package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.SizeType;
import telerikProject.models.workItems.contracts.Story;

import java.util.List;
import java.util.stream.Stream;

public class ChangeStorySizeCommand extends CommandImpl {

    private Story existingStory;
    private SizeType sizeType;

    public ChangeStorySizeCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Size of a Story command parameters.");
        }

        existingStory.setSizeType(sizeType);

        writingHistoryFile();

        return String.format("Size type for story with ID %d was changed to %s."
                , existingStory.getId(), existingStory.getSizeType());
    }

    private void writingHistoryFile() {
        existingStory.getHistory().add(String.format("Size type for story with ID %d was changed to %s"
                , existingStory.getId(), existingStory.getSizeType()));
    }

    private void parseParameters(List<String>parameters){
        existingStory = engine.getWorkItems()
                .stream()
                .filter(story -> parameters.get(0).equals(Integer.toString(story.getId())))
                .map(p->(Story)p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        sizeType = Stream.of(SizeType.values())
                .filter(SizeType -> parameters.get(1).toUpperCase().equals(SizeType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
