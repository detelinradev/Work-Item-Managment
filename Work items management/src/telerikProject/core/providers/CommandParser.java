package telerikProject.core.providers;

import telerikProject.commands.add.*;
import telerikProject.commands.assign.AssignWorkItemCommand;
import telerikProject.commands.change.*;
import telerikProject.commands.contracts.Command;
import telerikProject.commands.create.*;
import telerikProject.commands.filter.FilterWorkItemsByAssigneeCommand;
import telerikProject.commands.filter.FilterWorkItemsByStatusAndAssigneeCommand;
import telerikProject.commands.filter.FilterWorkItemsByStatusCommand;
import telerikProject.commands.list.*;
import telerikProject.commands.unassign.UnassignWorkItemCommand;
import telerikProject.commands.show.*;
import telerikProject.commands.sort.*;
import telerikProject.core.contracts.Engine;
import telerikProject.core.contracts.Parser;
import telerikProject.core.factories.CreationsFactory;

import java.util.*;

public class CommandParser implements Parser {
    private static final String INVALID_COMMAND = "Invalid command name: \"%s\"!";

    private final CreationsFactory factory;
    private final Engine engine;

    public CommandParser(CreationsFactory factory, Engine engine) {
        this.factory = factory;
        this.engine = engine;
    }

    public Command parseCommand(String fullCommand) {
        if (!fullCommand.contains("_")) {
            throw new IllegalArgumentException(String.format(INVALID_COMMAND,fullCommand));
        }

        String commandName = fullCommand.split("_")[0];
            String commandSecond = fullCommand.split("_")[1];

        return findCommand(commandName, commandSecond);
    }

    public List<String> parseParameters(String fullCommand) {
        String[] commandParts = fullCommand.split("_");
        if (Arrays.stream(commandParts).anyMatch(String::isEmpty))
            throw new IllegalArgumentException("Commands should not be empty!");
        return new ArrayList<>(Arrays.asList(commandParts).subList(2, commandParts.length));
    }

    private Command findCommand(String commandName, String commandSecond) {

        switch (commandName.toLowerCase()) {

            case "create":
                switch (commandSecond.toLowerCase()) {
                    case "person":
                        return new CreateMemberCommand(factory, engine);
                    case "team":
                        return new CreateTeamCommand(factory, engine);
                    case "board":
                        return new CreateBoardCommand(factory, engine);
                    case "bug":
                        return new CreateBugCommand(factory, engine);
                    case "story":
                        return new CreateStoryCommand(factory, engine);
                    case "feedback":
                        return new CreateFeedbackCommand(factory, engine);
                }
            case "add":
                switch (commandSecond.toLowerCase()) {
                    case "person":
                        return new AddPersonToTeamCommand(factory, engine);
                    case "comment to bug":
                        return new AddCommentBugCommand(factory, engine);
                    case "comment to feedback":
                        return new AddCommentFeedbackCommand(factory, engine);
                    case "comment to story":
                        return new AddCommentStoryCommand(factory, engine);
                }
            case "assign":
                if ("work item".equals(commandSecond.toLowerCase())) {
                    return new AssignWorkItemCommand(factory, engine);
                }
            case "change":
                switch (commandSecond.toLowerCase()) {
                    case "bug severity":
                        return new ChangeBugSeverityCommand(factory, engine);
                    case "bug priority":
                        return new ChangeBugPriorityCommand(factory, engine);
                    case "bug status":
                        return new ChangeBugStatusCommand(factory, engine);
                    case "feedback rating":
                        return new ChangeFeedbackRatingCommand(factory, engine);
                    case "feedback status":
                        return new ChangeFeedbackStatusCommand(factory, engine);
                    case "story priority":
                        return new ChangeStoryPriorityCommand(factory, engine);
                    case "story size":
                        return new ChangeStorySizeCommand(factory, engine);
                    case "story status":
                        return new ChangeStoryStatusCommand(factory, engine);
                }
            case "unassign" :
                if ("work item".equals(commandSecond.toLowerCase())) {
                    return new UnassignWorkItemCommand(factory, engine);
                }
            case "show":
                switch (commandSecond.toLowerCase()) {
                    case "people":
                        return new ShowMembersCommand(factory, engine);
                    case "teams":
                        return new ShowTeamsCommand(factory, engine);
                    case "member activity":
                        return new ShowMemberActivityCommand(factory, engine);
                    case "board activity":
                        return new ShowBoardActivityCommand(factory, engine);
                    case "team activity":
                        return new ShowTeamActivityCommand(factory, engine);
                    case "team members":
                        return new ShowTeamMembersCommand(factory, engine);
                    case "team boards":
                        return new ShowTeamBoardsCommand(factory, engine);
                }

            case "list":
                switch (commandSecond.toLowerCase()) {
                    case "bugs":
                        return new ListBugsCommand(factory, engine);
                    case "feedback":
                        return new ListFeedbackCommand(factory, engine);
                    case "stories":
                        return new ListStoriesCommand(factory, engine);
                    case "work items":
                        return new ListAllWorkItemsCommand(factory, engine);
                }
            case "filter":
                switch (commandSecond.toLowerCase()) {
                    case "work items status":
                        return new FilterWorkItemsByStatusCommand(factory, engine);
                    case "work items assignee":
                        return new FilterWorkItemsByAssigneeCommand(factory, engine);
                    case "work items status assignee":
                        return new FilterWorkItemsByStatusAndAssigneeCommand(factory, engine);
                }
            case "sort":
                switch (commandSecond.toLowerCase()) {
                    case "title":
                        return new SortByTitleCommand(factory, engine);
                    case "rating":
                        return new SortByRatingCommand(factory, engine);
                    case "priority":
                        return new SortByPriorityCommand(factory, engine);
                    case "severity":
                        return new SortBySeverityCommand(factory, engine);
                    case "size":
                        return new SortBySizeCommand(factory, engine);
                }
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
    }
}
