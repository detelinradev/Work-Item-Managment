package telerikProject.commands.list;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class ListAllWorkItemsCommand extends CommandImpl {
    public ListAllWorkItemsCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    @Override
    public String execute(List<String> parameters) {
        List<WorkItem> workItems = engine.getWorkItems();
        List<String> listWorkItems = new ArrayList<>();
        for(WorkItem workItem : workItems){
            listWorkItems.add(workItem.toString());
        }
        if(listWorkItems.isEmpty())
            listWorkItems.add("There are no registered work items.");
        return "\n--------------------\nLIST OF ALL WORK ITEMS:\n"
                +String.join("",listWorkItems).trim();
    }
}
