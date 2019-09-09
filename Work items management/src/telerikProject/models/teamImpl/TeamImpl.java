package telerikProject.models.teamImpl;


import telerikProject.models.team.contracts.Board;
import telerikProject.models.team.contracts.Member;
import telerikProject.models.team.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private String name;
    private List<Member> memberList;
    private List<Board> boardList;

    public TeamImpl(String name) {
        setName(name);
        memberList = new ArrayList<>();
        boardList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Member> getMemberList() {
        return memberList;
    }

    @Override
    public List<Board> getBoardList() {
        return boardList;
    }
}
