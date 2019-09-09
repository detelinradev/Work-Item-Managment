package telerikProject.commands.change;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.workItems.contracts.Bug;

import java.util.List;
import java.util.stream.Stream;

public class ChangeBugSeverityCommand extends CommandImpl {

    private Bug existingBug;
    private SeverityType severityType;

    public ChangeBugSeverityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Change Severity of a Bug command parameters.");
        }

        existingBug.setSeverityType(severityType);

        writingHistoryFile();

        return String.format("Severity type for bug with ID %d was changed to %s."
                , existingBug.getId(), existingBug.getSeverityType());
    }

    private void writingHistoryFile() {
        existingBug.getHistory().add(String.format("Severity type for bug with ID %d was changed to %s"
                , existingBug.getId(), existingBug.getSeverityType()));
    }

    private void parseParameters(List<String> parameters) {
        existingBug = engine.getWorkItems()
                .stream()
                .filter(workItem -> parameters.get(0).equals(Integer.toString(workItem.getId())))
                .map(p -> (Bug) p)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        severityType = Stream.of(SeverityType.values())
                .filter(SeverityType -> parameters.get(1).toUpperCase().equals(SeverityType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
