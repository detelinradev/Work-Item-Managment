package telerikProjectUnitTests.coreTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import telerikProject.commands.add.AddCommentBugCommand;
import telerikProject.commands.add.AddCommentFeedbackCommand;
import telerikProject.commands.add.AddCommentStoryCommand;
import telerikProject.commands.add.AddPersonToTeamCommand;
import telerikProject.commands.change.*;
import telerikProject.commands.create.*;
import telerikProject.commands.list.ListAllWorkItemsCommand;
import telerikProject.commands.list.ListBugsCommand;
import telerikProject.commands.list.ListFeedbackCommand;
import telerikProject.commands.list.ListStoriesCommand;
import telerikProject.commands.show.*;
import telerikProject.commands.sort.*;
import telerikProject.core.EngineImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.factories.CreationsFactoryImpl;
import telerikProject.core.providers.CommandParser;


public class CommandParserTests {
    private CreationsFactory factory;
    private Engine engine;
    private String fullCommand;

@Before
public void asdasd() {
    factory = new CreationsFactoryImpl();
    engine = new EngineImpl(factory);
}

    @Test
    public void FindCommandCreatePerson_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){

        CreateMemberCommand createMemberCommand = new CreateMemberCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="create_person";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(createMemberCommand);
    }
    @Test
    public void FindCommandCreateBoard_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        CreateBoardCommand createBoardCommand = new CreateBoardCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="create_board";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(createBoardCommand);
    }
    @Test
    public void FindCommandCreateBug_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        CreateBugCommand createBugCommand = new CreateBugCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="create_bug";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(createBugCommand);
    }
    @Test
    public void FindCommandCreateFeedback_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        CreateFeedbackCommand createFeedbackCommand = new CreateFeedbackCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="create_feedback";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(createFeedbackCommand);
    }
    @Test
    public void FindCommandCreateStory_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        CreateStoryCommand createStoryCommand = new CreateStoryCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="create_story";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(createStoryCommand);
    }
    @Test
    public void FindCommandCreateTeam_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        CreateTeamCommand createTeamCommand = new CreateTeamCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="create_team";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(createTeamCommand);
    }
    @Test
    public void FindCommandAddCommentBug_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        AddCommentBugCommand addCommentBugCommand = new AddCommentBugCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="add_comment to bug";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(addCommentBugCommand);
    }
    @Test
    public void FindCommandAddCommentFeedback_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        AddCommentFeedbackCommand addCommentFeedbackCommand = new AddCommentFeedbackCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="add_comment to feedback";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(addCommentFeedbackCommand);
    }
    @Test
    public void FindCommandAddCommentStory_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        AddCommentStoryCommand addCommentStoryCommand = new AddCommentStoryCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="add_comment to story";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(addCommentStoryCommand);
    }
    @Test
    public void FindCommandAddPersonToTeam_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        AddPersonToTeamCommand addPersonToTeamCommand = new AddPersonToTeamCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="add_person";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(addPersonToTeamCommand);
    }
    @Test
    public void FindCommandChangeBugPriority_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeBugPriorityCommand changeBugPriorityCommand = new ChangeBugPriorityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_bug priority";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeBugPriorityCommand);
    }
    @Test
    public void FindCommandChangeBugSeverity_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeBugSeverityCommand changeBugSeverityCommand = new ChangeBugSeverityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_bug severity";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeBugSeverityCommand);
    }
    @Test
    public void FindCommandChangeBugStatus_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeBugStatusCommand changeBugStatusCommand = new ChangeBugStatusCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_bug status";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeBugStatusCommand);
    }
    @Test
    public void FindCommandChangeFeedbackRating_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeFeedbackRatingCommand changeFeedbackRatingCommand = new ChangeFeedbackRatingCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_feedback rating";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeFeedbackRatingCommand);
    }
    @Test
    public void FindCommandChangeFeedbackStatus_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeFeedbackStatusCommand changeFeedbackStatusCommand = new ChangeFeedbackStatusCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_feedback status";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeFeedbackStatusCommand);
    }
    @Test
    public void FindCommandChangeStoryPriority_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeStoryPriorityCommand changeStoryPriorityCommand = new ChangeStoryPriorityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_story priority";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeStoryPriorityCommand);
    }
    @Test
    public void FindCommandChangeStorySize_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeStorySizeCommand changeStorySizeCommand = new ChangeStorySizeCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_story size";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeStorySizeCommand);
    }
    @Test
    public void FindCommandChangeStoryStatus_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ChangeStoryStatusCommand changeStoryStatusCommand = new ChangeStoryStatusCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="change_story status";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(changeStoryStatusCommand);
    }
    @Test
    public void FindCommandShowBoardActivity_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ShowBoardActivityCommand showBoardActivityCommand = new ShowBoardActivityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="show_board activity";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(showBoardActivityCommand);
    }
    @Test
    public void FindCommandShowMemberActivity_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ShowMemberActivityCommand showMemberActivityCommand = new ShowMemberActivityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="show_member activity";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(showMemberActivityCommand);
    }
    @Test
    public void FindCommandShowMembers_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ShowMembersCommand showMembersCommand = new ShowMembersCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="show_people";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(showMembersCommand);
    }
    @Test
    public void FindCommandShowTeamActivity_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ShowTeamActivityCommand showTeamActivityCommand = new ShowTeamActivityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="show_team activity";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(showTeamActivityCommand);
    }
    @Test
    public void FindCommandShowTeamBoards_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ShowTeamBoardsCommand showTeamBoardsCommand = new ShowTeamBoardsCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="show_team boards";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(showTeamBoardsCommand);
    }
    @Test
    public void FindCommandShowTeamMembers_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ShowTeamMembersCommand showTeamMembersCommand = new ShowTeamMembersCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="show_team members";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(showTeamMembersCommand);
    }
    @Test
    public void FindCommandShowTeamsCommand_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ShowTeamsCommand showTeamsCommand = new ShowTeamsCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="show_teams";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(showTeamsCommand);
    }
    @Test
    public void FindCommandListBugs_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ListBugsCommand listBugsCommand = new ListBugsCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="list_bugs";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(listBugsCommand);
    }
    @Test
    public void FindCommandListFeedback_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ListFeedbackCommand listFeedbackCommand = new ListFeedbackCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="list_feedback";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(listFeedbackCommand);
    }
    @Test
    public void FindCommandListStories_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ListStoriesCommand listStoriesCommand = new ListStoriesCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="list_stories";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(listStoriesCommand);
    }
    @Test
    public void FindCommandListWorkItems_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        ListAllWorkItemsCommand listAllWorkItemsCommand = new ListAllWorkItemsCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="list_work items";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(listAllWorkItemsCommand);
    }
    @Test
    public void FindCommandSortTitle_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        SortByTitleCommand sortByTitleCommand = new SortByTitleCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="sort_title";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(sortByTitleCommand);
    }
    @Test
    public void FindCommandSortPriority_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        SortByPriorityCommand sortByPriorityCommand = new SortByPriorityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="sort_priority";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(sortByPriorityCommand);
    }
    @Test
    public void FindCommandSortRating_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        SortByRatingCommand sortByRatingCommand = new SortByRatingCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="sort_rating";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(sortByRatingCommand);
    }
    @Test
    public void FindCommandSortSeverity_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        SortBySeverityCommand sortBySeverityCommand = new SortBySeverityCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="sort_severity";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(sortBySeverityCommand);
    }
    @Test
    public void FindCommandSortSize_ShouldCreateNewObjectCommand_WhenCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        SortBySizeCommand sortBySizeCommand = new SortBySizeCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="sort_size";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(sortBySizeCommand);
    }
    @Test(expected = IllegalArgumentException.class)
    public void FindCommandSortTitle_ShouldThrowException_WhenUnCorrectParametersPassed(){
        factory = new CreationsFactoryImpl();
        engine = new EngineImpl(factory);
        SortByTitleCommand sortByTitleCommand = new SortByTitleCommand(factory,engine);
        CommandParser commandParser = new CommandParser(factory,engine);
        fullCommand ="sort_title1";
        commandParser.parseCommand(fullCommand);
        Assert.assertNotNull(sortByTitleCommand);
    }



}
