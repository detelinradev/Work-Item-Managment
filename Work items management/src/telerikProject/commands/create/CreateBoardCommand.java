package telerikProject.commands.create;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Team;

import java.util.List;

public class CreateBoardCommand extends CommandImpl {

    private Team teamName;
    private String name;

    public CreateBoardCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        Board board;
        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Create Board command parameters.");
        }

        if (checkUniqueNameInTeam(parameters)) {
            board = factory.createBoard(name, teamName.getName());
        } else throw new IllegalArgumentException(String.format("Board with name %s already exists!", name));

        listingNewBoard(board);

        writingHistoryFile(board);

        return String.format("Board with name %s was created in team %s."
                , board.getName(), teamName.getName());
    }

    private void listingNewBoard(Board board) {
        teamName.getBoardList().add(board);
        engine.getBoards().add(board);
    }

    private void writingHistoryFile(Board board) {
        board.getHistory().add(String.format("Board with name %s was created in team %s"
                , board.getName(), teamName.getName()));
    }

    private boolean checkUniqueNameInTeam(List<String> parameters) {
        return teamName.getBoardList()
                .stream()
                .noneMatch(board -> board.getName().equals(parameters.get(1)));
    }

    private void parseParameters(List<String> parameters) {
        teamName = engine.getTeams()
                .stream()
                .filter(team -> parameters.get(0).equals(team.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        name = parameters.get(1);
    }
}
