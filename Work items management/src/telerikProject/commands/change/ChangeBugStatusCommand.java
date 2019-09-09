package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.workItems.contracts.Bug;

import java.util.List;
import java.util.stream.Stream;

public class ChangeBugStatusCommand extends CommandImpl {

    private Bug existingBug;
    private StatusTypeBug statusTypeBug;

    public ChangeBugStatusCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Status of a Bug command parameters.");
        }

        existingBug.setStatusTypeBug(statusTypeBug);

        writingHistoryFile();

        return String.format("Status type for bug with ID %d was changed to %s."
                , existingBug.getId(), existingBug.getStatusType());
    }

    private void writingHistoryFile() {
        existingBug.getHistory().add(String.format("Status type for bug with ID %d was changed to %s"
                , existingBug.getId(), existingBug.getStatusType()));
    }

    private void parseParameters(List<String> parameters) {
        existingBug = engine.getWorkItems()
                .stream()
                .filter(workItem -> parameters.get(0).equals(Integer.toString(workItem.getId())))
                .map(p -> (Bug) p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        statusTypeBug = Stream.of(StatusTypeBug.values())
                .filter(StatusType -> parameters.get(1).toUpperCase().equals(StatusType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
