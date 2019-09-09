package telerikProject.models.teamImpl;

import telerikProject.models.team.contracts.Board;
import telerikProject.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 10;
    private static final String NAME_MUST_BE_BETWEEN_MIN_AND_MAX_CHARACTERS = "Name must be between %d and %d characters";

    private String name;
    private List<WorkItem> workItems;
    private List<String> history;
    private String teamName;


    public BoardImpl(String name, String teamName) {
        workItems = new ArrayList<>();
        history = new ArrayList<>();
        setName(name);
        setTeamName(teamName);
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getHistory() {
        return history ;
    }

    public void setName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(NAME_MUST_BE_BETWEEN_MIN_AND_MAX_CHARACTERS,
                    MIN_NAME_LENGTH, MAX_NAME_LENGTH));
        }
        this.name = name;
    }

    public List<WorkItem> getWorkItems() {
        return new ArrayList<>(workItems);
    }

    private void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
