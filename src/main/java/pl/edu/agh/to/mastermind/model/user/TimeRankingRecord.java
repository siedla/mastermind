package pl.edu.agh.to.mastermind.model.user;

public class TimeRankingRecord {
    private final String username;
    private final int seconds;


    public TimeRankingRecord(String username, int seconds) {
        this.username = username;
        this.seconds = seconds;
    }

    public String getUsername(){
        return this.username;
    }

    public int getSeconds(){
        return seconds;
    }
}
