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
import telerikProject.models.team.contracts.Member;
import telerikProject.models.teamImpl.BoardImpl;
import telerikProject.models.teamImpl.MemberImpl;
import telerikProject.models.workItems.contracts.WorkItem;

public class MemberImplTests {
    private CreationsFactory factory;
    private Member member;

    @Before
    public void initConstants(){
        factory = new CreationsFactoryImpl();
        member = new MemberImpl("membername");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createMember_ShouldThrowException_WhenNameLessThanMin(){
        member = new MemberImpl("m");
    }

    @Test
    public void createMember_ShouldGetTeamName_WhenCorrectParsedParameters(){
        Assert.assertNotNull(member.getTeamName());
    }

    @Test
    public void createMember_ShouldGetMemberName_WhenCorrectParsedParameters(){
        Assert.assertNotNull(member.getName());
    }

    @Test
    public void createMember_ShouldGetWorkItemsList_WhenCorrectParsedParameters(){
        Board board = new BoardImpl("boardname", "telerik");
        WorkItem workItem = factory.createBug("imetonaitema","description",
                StatusTypeBug.ACTIVE,1, PriorityType.HIGH, SeverityType.CRITICAL, board);
        member.getWorkItems().add(workItem);
        Assert.assertNotNull(member.getWorkItems());
    }

    @Test
    public void createMember_ShouldGetHistory_WhenCorrectParsedParameters(){
        member.getHistory().add("workItem was assigned to member");
        Assert.assertNotNull(member.getHistory());
    }
}
