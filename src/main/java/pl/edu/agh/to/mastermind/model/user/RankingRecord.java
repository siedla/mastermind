package pl.edu.agh.to.mastermind.model.user;

public class RankingRecord {
    private final String username;
    private final int wins;
    private final int rounds;
    private final double ratio;

    public RankingRecord(String username, int wins, int rounds){
        this.username=username;
        this.wins=wins;
        this.rounds=rounds;
        this.ratio=(double) Math.round(rounds*100/wins)/100;
    }

    public String getUsername(){
        return this.username;
    }

    public int getWins(){
        return wins;
    }

    public int getRounds(){
        return this.rounds;
    }
    public double getRatio(){
        return this.ratio;
    }

}
