package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.workItems.contracts.Feedback;

import java.util.List;
import java.util.stream.Stream;

public class ChangeFeedbackStatusCommand extends CommandImpl {

    private Feedback existingFeedback;
    private StatusTypeFeedback statusTypeFeedback;

    public ChangeFeedbackStatusCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Status of a Feedback command parameters.");
        }

        existingFeedback.setStatusTypeFeedback(statusTypeFeedback);

        writingHistoryFile();

        return String.format("Status type for feedback with ID %d was changed to %s."
                , existingFeedback.getId(), existingFeedback.getStatusType());
    }

    private void writingHistoryFile() {
        existingFeedback.getHistory().add(String.format("Status type for feedback with ID %d was changed to %s"
                , existingFeedback.getId(), existingFeedback.getStatusType()));
    }

    private void parseParameters(List<String> parameters) {
        existingFeedback = engine.getWorkItems()
                .stream()
                .filter(feedback -> parameters.get(0).equals(Integer.toString(feedback.getId())))
                .map(p -> (Feedback) p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        statusTypeFeedback = Stream.of(StatusTypeFeedback.values())
                .filter(StatusType -> parameters.get(1).toUpperCase().equals(StatusType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
