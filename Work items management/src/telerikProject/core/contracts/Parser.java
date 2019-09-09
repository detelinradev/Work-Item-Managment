package telerikProject.core.contracts;

import telerikProject.commands.contracts.Command;

import java.util.List;

public interface Parser {
    Command parseCommand(String fullCommand);

    List<String> parseParameters(String fullCommand);
}
