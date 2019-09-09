package telerikProject.commands.show;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamBoardsCommand extends CommandImpl {

    private Team existingTeam;

    public ShowTeamBoardsCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {

        try {
            parseParameters(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse Show Team Members command parameters.");
        }

        if (existingTeam.getBoardList().size() == 0) {
            throw new IllegalArgumentException("There are no registered boards in the team.");
        }
        List<Board> listTeamBoards = new ArrayList<>(existingTeam.getBoardList());
        List<String> listBoardNames = new ArrayList<>();
        for(Board board: listTeamBoards){
            listBoardNames.add("  " + board.getName());
        }

        return String.format("\n--------------------\nTeam: %s\n Boards:\n%s\n--------------------"
                , existingTeam.getName(),  String.join(","
                        + System.lineSeparator(), listBoardNames));
    }

    private void parseParameters(List<String> parameters) {
        existingTeam = engine.getTeams()
                .stream()
                .filter(team -> parameters.get(0).equals(team.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
