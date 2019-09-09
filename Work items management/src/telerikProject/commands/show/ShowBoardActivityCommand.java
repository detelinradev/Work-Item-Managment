package telerikProject.commands.show;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Board;

import java.util.ArrayList;
import java.util.List;

public class ShowBoardActivityCommand extends CommandImpl {

    private Board existingBoard;

    public ShowBoardActivityCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Show Board Activity command parameters.");
        }

        List<String> listBoardHistory = new ArrayList<>();
        for (String history : existingBoard.getHistory()) {
            listBoardHistory.add("  " + history);
        }

        return String.format("\n--------------------\nBoard: %s\n Activity:\n%s\n--------------------"
                , existingBoard.getName()
                , String.join("," + System.lineSeparator()
                        , listBoardHistory));
    }

    private void parseParameters(List<String> parameters) {
        existingBoard = engine.getBoards()
                .stream()
                .filter(board -> parameters.get(0).equals(board.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}