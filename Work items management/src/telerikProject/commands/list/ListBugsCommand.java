package telerikProject.commands.list;


import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.Bug;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListBugsCommand extends CommandImpl {
    public ListBugsCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {
        List<Bug> bugs = engine.getWorkItems()
                .stream()
                .filter(e -> e instanceof Bug)
                .map(p -> (Bug) p)
                .collect(Collectors.toList());
        if (bugs.size() == 0) {
            throw new IllegalArgumentException("There are no registered bugs.");
        }

        List<String> listBugs = new ArrayList<>();
        for (WorkItem bug : bugs) {
            listBugs.add(bug.toString());
        }
        if(listBugs.isEmpty())
            listBugs.add("There are no registered bugs.");


        return "\n--------------------\nLIST OF ALL BUGS:\n"
                + String.join("", listBugs).trim();
    }
}
