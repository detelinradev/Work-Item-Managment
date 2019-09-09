package telerikProject.models.workItemsImpl;


import telerikProject.models.enumTypes.StatusType;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.workItems.contracts.Feedback;


public class FeedbackImpl extends WorkItemsBase implements Feedback {

    private int rating;
    private StatusTypeFeedback statusTypeFeedback;

    public FeedbackImpl(String title, String description, int rating, StatusTypeFeedback statusTypeFeedback, int id, Board boardName) {
        super(title, description, id, boardName);
        setRating(rating);
        setStatusTypeFeedback(statusTypeFeedback);
    }

    @Override
    public StatusType getStatusType() {
        return statusTypeFeedback;
    }

    public void setStatusTypeFeedback(StatusTypeFeedback statusTypeFeedback){this.statusTypeFeedback = statusTypeFeedback;}

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    String header() {
        return String.format("%s", this.getClass().getSimpleName().replace("Impl", ""));
    }

    @Override
    String footer() {
        return String.format("Rating: %s", getRating());
    }

}
