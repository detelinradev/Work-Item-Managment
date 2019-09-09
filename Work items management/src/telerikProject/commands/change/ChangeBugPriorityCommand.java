package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.workItems.contracts.Bug;

import java.util.List;
import java.util.stream.Stream;

public class ChangeBugPriorityCommand extends CommandImpl {

    private Bug existingBug;
    private PriorityType priorityType;

    public ChangeBugPriorityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Priority of a Bug command parameters.");
        }

        existingBug.setPriorityType(priorityType);

        writingHistoryFile();

        return String.format("Priority type for bug with ID %d was changed to %s."
                , existingBug.getId(), existingBug.getPriorityType());
    }

    private void writingHistoryFile() {
        existingBug.getHistory().add(String.format("Priority type for bug with ID %d was changed to %s"
                , existingBug.getId(), existingBug.getPriorityType()));
    }

    private void parseParameters(List<String> parameters) {
        existingBug = engine.getWorkItems()
                .stream()
                .filter(workItem -> parameters.get(0).equals(Integer.toString(workItem.getId())))
                .map(p -> (Bug) p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        priorityType = Stream.of(PriorityType.values())
                .filter(PriorityType -> parameters.get(1).toUpperCase().equals(PriorityType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
