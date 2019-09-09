package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.Feedback;

import java.util.List;

public class ChangeFeedbackRatingCommand extends CommandImpl {

    private Feedback existingFeedback;
    private int newRating;

    public ChangeFeedbackRatingCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
           parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Rating of a Feedback command parameters.");
        }

        existingFeedback.setRating(newRating);

        writingHistoryFile();

        return String.format("Rating for feedback with ID %d was changed to %d."
                , existingFeedback.getId(), existingFeedback.getRating());
    }

    private void writingHistoryFile() {
        existingFeedback.getHistory().add(String.format("Rating for feedback with ID %d was changed to %d"
                , existingFeedback.getId(), existingFeedback.getRating()));
    }

    private void parseParameters(List<String>parameters){
        existingFeedback = engine.getWorkItems()
                .stream()
                .filter(feedback -> parameters.get(0).equals(Integer.toString(feedback.getId())))
                .map(p->(Feedback)p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        newRating = Integer.parseInt(parameters.get(1));
    }
}
