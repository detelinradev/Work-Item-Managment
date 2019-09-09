package telerikProject.core;

import telerikProject.commands.contracts.Command;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.core.providers.*;
import telerikProject.core.contracts.Engine;
import telerikProject.core.contracts.Parser;
import telerikProject.core.contracts.Reader;
import telerikProject.core.contracts.Writer;
import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class EngineImpl implements Engine {
    private static final String TERMINATION_COMMAND = "Exit";

    private Reader reader;
    private Writer writer;
    private Parser parser;
    private final List<Integer> workItemIDs;
    private final List<Team> teams;
    private final List<WorkItem> workItems;
    private final List<Board> boards;
    private final List<Member> members;

    public EngineImpl(CreationsFactory factory) {
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
        parser = new CommandParser(factory, this);

        workItemIDs = new ArrayList<>();
        teams = new ArrayList<>();
        workItems = new ArrayList<>();
        boards = new ArrayList<>();
        members = new ArrayList<>();
    }


    @Override
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public List<Board> getBoards() {
        return boards;
    }

    @Override
    public List<WorkItem> getWorkItems() {
        return workItems;
    }
    @Override
    public List<Member> getMembers() {
        return members;
    }

    @Override
    public List<Integer>getWorkItemIDs (){ return workItemIDs;}


    @Override
    public void start() {
        while (true) {
            try {
                String commandAsString = reader.readLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);
            } catch (Exception ex) {
                writer.writeLine(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : ex.toString());
            }
        }
    }

    private void processCommand(String commandAsString) {
        if (commandAsString == null || commandAsString.trim().equals("")) {
            throw new IllegalArgumentException("Command cannot be null or empty.");
        }
        Command command = parser.parseCommand(commandAsString);
        List<String> parameters = parser.parseParameters(commandAsString);
        String executionResult = command.execute(parameters);
        writer.writeLine(executionResult);
    }
}
