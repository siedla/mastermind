package pl.edu.agh.to.mastermind.model.user;

public class TimeRankingRecord {
    private final String username;
    private final int seconds;
    private final String email;


    public TimeRankingRecord(String username, int seconds) {
        this.username = username;
        this.seconds = seconds;
        this.email = "";
    }

    public TimeRankingRecord(String username, int seconds, String email) {
        this.username = username;
        this.seconds = seconds;
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getEmail() {
        return this.email;
    }
}
