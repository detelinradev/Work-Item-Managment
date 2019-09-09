package telerikProject.models.enumTypes;

public enum StatusTypeFeedback implements StatusType{
    DONE,
    NEW,
    UNSCHEDULED,
    SCHEDULED;

    @Override
    public String toString() {
        switch (this) {
            case DONE:
                return "Done";
            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            default:
                throw new IllegalArgumentException("Status type for feedback does not exist in the database!");
        }

    }
}
