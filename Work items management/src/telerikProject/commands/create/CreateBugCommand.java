package telerikProject.commands.create;


import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.enumTypes.*;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.workItems.contracts.Bug;

import java.util.List;
import java.util.stream.Stream;

public class CreateBugCommand extends CommandImpl {

    private String title;
    private String description;
    private StatusTypeBug statusTypeBug;
    private PriorityType priorityType;
    private SeverityType severityType;
    private Board existingBoard;
    private int id;

    public CreateBugCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Create Bug command parameters.");
        }

        Bug bug = factory.createBug(title, description, statusTypeBug, id, priorityType, severityType, existingBoard);

        listingNewBug(bug);

        writingHistoryFile(bug);

        return String.format("Bug with ID %d was created in board %s.",
                bug.getId(), existingBoard.getName());
    }

    private void listingNewBug(Bug bug) {
        engine.getWorkItems().add(bug);
        existingBoard.getWorkItems().add(bug);
    }

    private void writingHistoryFile(Bug bug) {
        existingBoard.getHistory().add(String.format("Bug with ID %d was created in board %s",
                bug.getId(), existingBoard.getName()));
        bug.getHistory().add(String.format("Bug with ID %d was created in board %s",
                bug.getId(), existingBoard.getName()));
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        statusTypeBug = Stream.of(StatusTypeBug.values())
                .filter(StatusType -> parameters.get(2).toUpperCase().equals(StatusType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        priorityType = Stream.of(PriorityType.values())
                .filter(PriorityType -> parameters.get(3).toUpperCase().equals(PriorityType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        severityType = Stream.of(SeverityType.values())
                .filter(SeverityType -> parameters.get(4).toUpperCase().equals(SeverityType.name()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        existingBoard = engine.getBoards()
                .stream()
                .filter(board -> parameters.get(5).equals(board.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
