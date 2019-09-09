package telerikProject.models.workItemsImpl;

import telerikProject.models.enumTypes.StatusType;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.*;


public abstract class WorkItemsBase implements WorkItem {

    private static final String TITLE_LENGTH_EXCEPTION = "Title's length " +
            "should be between 10 and 50 symbols!";
    private static final String DESCRIPTION_LENGTH_EXCEPTION = "Description's length " +
            "should be between 10 and 500 symbols!";
    private static final int MIN_TITLE_LENGTH = 10;
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final int MIN_DESCRIPTION_LENGTH = 10;

    private int id;
    private String title;
    private String description;
    private Map<String, String> comments;
    private List<String> history;
    private Board boardName;

    WorkItemsBase(String title, String description,
                   int id, Board boardName) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setBoardName(boardName);
        comments = new HashMap<>();
        history = new ArrayList<>();
    }

    public Board getBoard() {
        return boardName;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public List<String> getHistory() {
        return history ;
    }

    abstract String header();

    public String toString() {
        return (String.format("--------------------\n" +
                        "%s" +
                        " ID: %s\n" +
                        " Title: %s\n" +
                        " Description: %s\n" +
                        " Status: %s\n" +
                        " Comments:\n%s\n" +
                        " History:\n%s\n" +
                        " %s\n" +
                        "--------------------\n",
                header(),
                getId(),
                getTitle(),
                getDescription(),
                getStatusType(),
                printComments(comments).isEmpty() ? "  There are no comments for this work item." :printComments(comments),
                printHistory(history).isEmpty()? "  There is no history for this work item":printHistory(history),
                footer()));
    }

    abstract String footer();

    private String printComments(Map<String,String>comments){
        List<String> commentsPrintFormatted = new ArrayList<>();
        for(String author:comments.keySet()){
            commentsPrintFormatted.add(String.format("  <Author: %s, comment: %s>",author,comments.get(author)));
        }

        return String.join("," + System.lineSeparator(),commentsPrintFormatted);
    }

    private String printHistory(List<String>history){
        List<String>historyPrintFormatted = new ArrayList<>();
        for(String historyContent:history){
            historyPrintFormatted.add(String.format("  <%s>",historyContent));
        }
        return String.join("," + System.lineSeparator(),historyPrintFormatted);
    }

    private void setBoardName(Board boardName) {
        this.boardName = boardName;
    }

    private void setTitle(String title) {
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH)
            throw new IllegalArgumentException(TITLE_LENGTH_EXCEPTION);
        this.title = title;
    }

    private void setDescription(String description) {
        if (description.length() < MIN_DESCRIPTION_LENGTH || description.length() > MAX_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException(DESCRIPTION_LENGTH_EXCEPTION);
        this.description = description;
    }
}
