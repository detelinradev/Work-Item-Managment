package telerikProject.commands.create;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.workItems.contracts.Feedback;

import java.util.List;
import java.util.stream.Stream;

public class CreateFeedbackCommand extends CommandImpl {

    private String title;
    private String description;
    private StatusTypeFeedback statusTypeFeedback;
    private int rating;
    private Board existingBoard;
    private int id;

    public CreateFeedbackCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Create Feedback command parameters.");
        }
        Feedback feedback = factory.createFeedback(title, description, rating, statusTypeFeedback, id, existingBoard);

        listingNewBug(feedback);

        writingHistoryFile(feedback);

        return String.format("Feedback with ID %d was created in board %s.",
                feedback.getId(), existingBoard.getName());
    }

    private void listingNewBug(Feedback feedback) {
        engine.getWorkItems().add(feedback);
        existingBoard.getWorkItems().add(feedback);
    }

    private void writingHistoryFile(Feedback feedback) {
        existingBoard.getHistory().add(String.format("Feedback with ID %d was created in board %s",
                feedback.getId(), existingBoard.getName()));
        feedback.getHistory().add(String.format("Feedback with ID %d was created in board %s",
                feedback.getId(), existingBoard.getName()));
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        rating = Integer.parseInt(parameters.get(2));
        statusTypeFeedback = Stream.of(StatusTypeFeedback.values())
                .filter(StatusType -> parameters.get(3).toUpperCase().equals(StatusType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        existingBoard = engine.getBoards()
                .stream()
                .filter(board -> parameters.get(4).equals(board.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}
