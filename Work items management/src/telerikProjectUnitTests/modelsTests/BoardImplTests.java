package telerikProjectUnitTests.modelsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.models.enumTypes.PriorityType;
import telerikProject.models.enumTypes.SeverityType;
import telerikProject.models.enumTypes.StatusTypeBug;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.workItems.contracts.WorkItem;

public class BoardImplTests {
    private CreationsFactory factory;
    private Board board;

    @Before
    public void initConstants() {
        factory = new CreationsFactoryImpl();
        board = new BoardImpl("boardname", "telerik");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBoard__ShouldThrowException_WhenNameLengthBelowMin() {
        board = new BoardImpl("m", "telerik");
    }

    @Test
    public void createBoard_ShouldGetTeamName_WhenCorrectParsedParameters() {

        board.getTeamName();
        Assert.assertNotNull(board);
    }

    @Test
    public void createBoard_ShouldGetBoardName_WhenCorrectParsedParameters() {
        board.getName();
        Assert.assertNotNull(board.getName());
    }

    @Test
    public void createBoard_ShouldGetWorkItems_WhenCorrectParsedParameters() {
        WorkItem workItem = factory.createBug("imetonaitema", "description",
                StatusTypeBug.ACTIVE, 1, PriorityType.HIGH, SeverityType.CRITICAL, board);
        board.getWorkItems().add(workItem);
        Assert.assertNotNull(board.getWorkItems());
    }

    @Test
    public void createBoard_ShouldGetHistory_WhenCorrectParsedParameters() {
        board.getHistory().add("workItem was added to board");
        Assert.assertNotNull(board.getHistory());
    }
}
