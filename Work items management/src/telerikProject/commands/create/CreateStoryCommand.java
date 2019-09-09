package telerikProject.commands.create;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SizeType;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.workItems.contracts.Story;

import java.util.List;
import java.util.stream.Stream;

public class CreateStoryCommand extends CommandImpl {

    private String title;
    private String description;
    private StatusTypeStory statusTypeStory;
    private PriorityType priorityType;
    private SizeType sizeType;
    private Board existingBoard;
    private int id;

    public CreateStoryCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Create Story command parameters.");
        }

        Story story = factory.createStory(title, description, statusTypeStory, id, priorityType, sizeType, existingBoard);

        listingNewStory(story);

        writingHistoryFile(story);

        return String.format("Story with ID %d was created in board %s.",
                story.getId(), existingBoard.getName());
    }

    private void listingNewStory(Story story) {
        engine.getWorkItems().add(story);
        existingBoard.getWorkItems().add(story);
    }

    private void writingHistoryFile(Story story) {
        existingBoard.getHistory().add(String.format("Story with ID %d was created in board %s",
                story.getId(), existingBoard.getName()));
        story.getHistory().add(String.format("Story with ID %d was created in board %s",
                story.getId(), existingBoard.getName()));
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        statusTypeStory = Stream.of(StatusTypeStory.values())
                .filter(StatusType -> parameters.get(2).toUpperCase().equals(StatusType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        priorityType = Stream.of(PriorityType.values())
                .filter(PriorityType -> parameters.get(3).toUpperCase().equals(PriorityType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        sizeType = Stream.of(SizeType.values())
                .filter(SizeType -> parameters.get(4).toUpperCase().equals(SizeType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        existingBoard = engine.getBoards()
                .stream()
                .filter(board -> parameters.get(5).equals(board.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
