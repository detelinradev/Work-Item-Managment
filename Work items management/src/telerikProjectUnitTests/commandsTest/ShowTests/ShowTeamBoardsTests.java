package telerikProjectUnitTests.commandsTest.ShowTests;

import org.junit.Test;
import telerikProject.commands.show.ShowTeamBoardsCommand;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.TeamImpl;

import java.util.Arrays;
import java.util.Collections;

public class ShowTeamBoardsTests {
    @Test
    public void showTeamBoards_ShouldShowTeamBoards_WhenCorrectParsedParameters(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("boardName", "telerik");
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        team.getBoardList().add(board);
        ShowTeamBoardsCommand showTeamBoardsCommand = new ShowTeamBoardsCommand(factory,engine);
        showTeamBoardsCommand.execute(Collections.singletonList("telerik"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showTeamBoards_ShouldThrowException_WhenListIsEmpty(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("boardName", "telerik");
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        ShowTeamBoardsCommand showTeamBoardsCommand = new ShowTeamBoardsCommand(factory,engine);
        showTeamBoardsCommand.execute(Collections.singletonList("telerik"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showTeamBoards_ShouldThrowException_WhenWrongParametersAreParsed(){
        CreationsFactory factory = new CreationsFactoryImpl();
        Engine engine = new EngineImpl(factory);
        Board board = new BoardImpl("boardName", "telerik");
        Team team = new TeamImpl("telerik");
        engine.getTeams().add(team);
        team.getBoardList().add(board);
        ShowTeamBoardsCommand showTeamBoardsCommand = new ShowTeamBoardsCommand(factory,engine);
        showTeamBoardsCommand.execute(Collections.singletonList("teleriken"));
    }
}
