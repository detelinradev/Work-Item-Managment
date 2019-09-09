package telerikProject.models.workItems.contracts;


import telerikProject.models.enumTypes.StatusTypeFeedback;

public interface Feedback extends WorkItem {

    int getRating();

    void setRating(int rating);

    void setStatusTypeFeedback(StatusTypeFeedback statusTypeFeedback);
}
