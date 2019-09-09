package telerikProject.commands.filter;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.StatusType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.enumTypes.StatusTypeFeedback;
import telerikProject.models.enumTypes.StatusTypeStory;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterWorkItemsByStatusCommand extends CommandImpl {

    private StatusType statusType;

    public FilterWorkItemsByStatusCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

      try{
          parseParameters(parameters);
      }catch (Exception e){
          throw new IllegalArgumentException("Failed to parse List WorkItems By Status command parameters.");
      }

        List<WorkItem> workItemWithStatus;
            workItemWithStatus = engine.getWorkItems()
                    .stream()
                    .filter(e -> e.getStatusType().equals(statusType))
                    .collect(Collectors.toList());

        if (workItemWithStatus.size() == 0) {
            throw new IllegalArgumentException( "There are no registered work items with status.");
        }

        List<String> listWorkItemsWithStatus = new ArrayList<>();
        for (WorkItem workItem : workItemWithStatus) {
            listWorkItemsWithStatus.add(workItem.toString());
        }

        return String.format("\n--------------------\nWORK ITEMS WITH STATUS: %s\n"
                ,parameters.get(0))+String.join("", listWorkItemsWithStatus).trim();
    }

    private void parseParameters(List<String> parameters) {
        try {
            statusType = Stream.of(StatusTypeBug.values())
                    .filter(StatusType -> parameters.get(0).toUpperCase().equals(StatusType.name()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
            statusType = Stream.of(StatusTypeStory.values())
                    .filter(StatusType -> parameters.get(0).toUpperCase().equals(StatusType.name()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
            statusType = Stream.of(StatusTypeFeedback.values())
                    .filter(StatusType -> parameters.get(0).toUpperCase().equals(StatusType.name()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }catch (Exception ignored){}
        if(statusType == null) throw new IllegalArgumentException();
    }
}
