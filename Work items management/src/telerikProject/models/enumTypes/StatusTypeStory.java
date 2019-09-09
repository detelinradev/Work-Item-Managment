package telerikProject.models.enumTypes;

public enum StatusTypeStory implements StatusType{

    NOTDONE,
    DONE,
    INPROGRESS;


    @Override
    public String toString() {
        switch (this) {
            case DONE:
                return "Done";
            case NOTDONE:
                return "NotDone";
            case INPROGRESS:
                return "InProgress";
            default:
                throw new IllegalArgumentException("Status type does not exist in the database!");
        }
    }
}
