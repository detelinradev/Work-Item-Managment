package telerikProject.commands.list;

import telerikProject.commands.CommandImpl;
import telerikProject.core.contracts.Engine;
import telerikProject.core.factories.CreationsFactory;
import telerikProject.models.workItems.contracts.Feedback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListFeedbackCommand extends CommandImpl {
    public ListFeedbackCommand(CreationsFactory factory, Engine engine) {
        super(factory, engine);
    }

    public String execute(List<String> parameters) {
        List<Feedback> feedback = engine.getWorkItems()
                .stream()
                .filter(p -> p instanceof Feedback)
                .map(k -> (Feedback) k)
                .collect(Collectors.toList());

        if (feedback.size() == 0) {
            throw new IllegalArgumentException("There are no registered feedback.");
        }

        List<String> listFeedback = new ArrayList<>();
        for (Feedback feedback1 : feedback) {
            listFeedback.add(feedback1.toString());
        }
        if(listFeedback.isEmpty())
            listFeedback.add("There are no registered feedback.");

        return "\n--------------------\nLIST OF ALL FEEDBACK:\n"
                + String.join("" , listFeedback).trim();
    }
}
