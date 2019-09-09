package telerikProject.models.enumTypes;

public enum StatusTypeBug implements StatusType {

    ACTIVE,
    FIXED;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case FIXED:
                return "Fixed";
            default:
                throw new IllegalArgumentException("Status type for bug does not exist in the database!");
        }
    }
}
