package telerikProject.commands;

import telerikProject.commands.contracts.Command;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;

public abstract class CommandImpl implements Command {

    protected final CreationsFactory factory;
    protected final Engine engine;

    public CommandImpl(CreationsFactory factory, Engine engine) {
        this.factory = factory;
        this.engine = engine;
    }
}
