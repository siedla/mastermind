package pl.edu.agh.to.mastermind.model.user;

public class RankingRecord {
    private final String username;
    private final int wins;
    private final int avgrounds;

    public RankingRecord(String username, int wins, int avgrounds){
        this.username=username;
        this.wins=wins;
        this.avgrounds=avgrounds;
    }

    public String getUsername(){
        return this.username;
    }

    public int getWins(){
        return wins;
    }

    public int getRounds(){
        return this.avgrounds;
    }


}
