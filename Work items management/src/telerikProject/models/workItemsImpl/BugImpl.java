package telerikProject.models.workItemsImpl;

import telerikProject.models.enumTypes.*;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.workItems.contracts.Bug;

import java.util.ArrayList;
import java.util.List;


public class BugImpl extends WorkItemsBase implements Bug {

    private StatusTypeBug statusTypeBug;
    private List<String> stepsToReproduce;
    private PriorityType priorityType;
    private SeverityType severityType;
    private List<Member> assignees;

    public BugImpl(String title, String description, StatusTypeBug statusTypeBug, int id,
                   PriorityType priorityType, SeverityType severityType, Board boardName) {
        super(title, description,id, boardName);
        stepsToReproduce = new ArrayList<>();
        setSeverityType(severityType);
        setPriorityType(priorityType);
        assignees = new ArrayList<>();
        setStatusTypeBug(statusTypeBug);
    }

    @Override
    public StatusTypeBug getStatusType() {
        return statusTypeBug;
    }
    public void setStatusTypeBug(StatusTypeBug statusTypeBug) {
        this.statusTypeBug = statusTypeBug;
    }

    public List<String> getStepsToReproduce() {
        return stepsToReproduce = new ArrayList<>(stepsToReproduce);
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public SeverityType getSeverityType() {
        return severityType;
    }

    public void setSeverityType(SeverityType severityType) {
        this.severityType = severityType;
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
        return String.format("Severity type: %s\n" +
                        " Priority type: %s\n" +
                        " Assignee: %s",
                getSeverityType(), getPriorityType(),printAssignees(assignees).isEmpty()?"  This bug is not assigned to a person."
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