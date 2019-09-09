package telerikProject.models.team.contracts;


import java.util.List;

public interface Team{
    String getName();

    List<Member> getMemberList();

    List<Board> getBoardList();

}
