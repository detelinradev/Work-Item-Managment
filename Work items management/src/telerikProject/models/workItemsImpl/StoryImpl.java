package telerikProject.models.workItemsImpl;

import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SizeType;
import telerikProject.models.enumTypes.StatusType;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends WorkItemsBase implements Story {

    private PriorityType priorityType;
    private SizeType sizeType;
    private List<Member> assignees;
    private StatusTypeStory statusTypeStory;

    public StoryImpl(String title, String description, StatusTypeStory statusTypeStory, int id, PriorityType priorityType, SizeType sizeType, Board boardName) {
        super(title, description,id, boardName);
        setPriorityType(priorityType);
        setSizeType(sizeType);
        assignees = new ArrayList<>();
        setStatusTypeStory(statusTypeStory);
    }

    @Override
    public StatusType getStatusType() {
        return statusTypeStory;
    }

    public void setStatusTypeStory(StatusTypeStory statusTypeStory) {
        this.statusTypeStory = statusTypeStory;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public List<Member> getAssignees() {
        return assignees;
    }

    @Override
    String header() {
        return String.format("%s", this.getClass().getSimpleName().replace("Impl", ""));
    }

    @Override
    String footer() {
        return String.format("Size type: %s\n" +
                        " Priority type: %s\n" +
                        " Assignee: %s",
                getSizeType(), getPriorityType()
                ,printAssignees(assignees).isEmpty()?"  This story is not assigned to a person."
                        :printAssignees(assignees) );
    }
    private String printAssignees(List<Member> assignees) {
        List<String> assigneesPrintFormatted = new ArrayList<>();
        for (Member assignee : assignees) {
            assigneesPrintFormatted.add(assignee.getName());
        }
        return assigneesPrintFormatted.toString().substring(1,assigneesPrintFormatted.toString().length()-1);
    }
}