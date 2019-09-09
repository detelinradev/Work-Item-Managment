package telerikProject.models.workItems.contracts;

import telerikProject.models.enumTypes.SizeType;
import telerikProject.models.enumTypes.StatusType;
import telerikProject.models.enumTypes.StatusTypeStory;

public interface Story extends WorkItemsWithPriority {

    SizeType getSizeType();

    void setStatusTypeStory(StatusTypeStory statusTypeStory);

    StatusType getStatusType();

    void setSizeType(SizeType sizeType);
}
