package pl.edu.agh.to.mastermind.model.dao;

import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.game.Difficulty;
import pl.edu.agh.to.mastermind.model.game.Game;
import pl.edu.agh.to.mastermind.model.user.RankingRecord;
import pl.edu.agh.to.mastermind.model.user.TimeRankingRecord;
import pl.edu.agh.to.mastermind.model.user.User;
import pl.edu.agh.to.mastermind.model.user.UserManagementException;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class DatabaseDAO implements DAO {
    private Connection connection;

    public DatabaseDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl:3306/pmakarew", "pmakarew", "tMiKdJD9H9zKZbTn");
        } catch (SQLException exception) {
            System.out.println("Could not connect to database, continuing without.");
        }
    }

    @Override
    public void storeGameResult(Session gameSession, int finalResult) {
        storeGameResult(gameSession, finalResult, Integer.MAX_VALUE);
    }

    @Override
    public void storeGameResult(Session gameSession, int finalResult, long time_ms) {
        if (connection == null) {
            return;
        }
        final var userID = gameSession.getUser().getId();
        final var numberOfRounds = gameSession.getGame().getCurrentRound();
        final var difficulty = gameSession.getDifficulty();
        final var result = finalResult == 0 ? 0 : 1;
        final var durationSeconds = time_ms / 1000;
        String query = "insert into Games (UserID, duration_seconds, number_of_rounds, game_won, difficulty)"
                + "values ('" + userID + "', '" + durationSeconds + "', '" + numberOfRounds + "', '" + result + "', '" + difficulty + "')";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Could not store result in database");
        }
    }

    public List<RankingRecord> getRanking(Difficulty difficulty) {
        String query = "select UserID, count(game_won), AVG(number_of_rounds) from Games where difficulty='" + difficulty.getDifficulty() + "' AND game_won=1 GROUP BY UserID ORDER BY count(game_won) DESC";
        List<RankingRecord> result = new LinkedList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int increment = 1;
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String username = "";
                try {
                    User user = User.getUserByID(userID);
                    username = user.getFirstName() + " " + user.getLastName();
                } catch (UserManagementException e) {
                    System.out.println("Cant get user with this id");
                }
                int nor = rs.getInt("count(game_won)");
                int rounds = rs.getInt("AVG(number_of_rounds)");
                result.add(new RankingRecord(username, nor, rounds));
                increment++;
                if (increment >= 10) break;
            }
        } catch (SQLException e) {
            System.err.println("Could not read results from database");
        }
        return result;
    }

    public List<TimeRankingRecord> getTimeRanking(Difficulty difficulty) {
        String query = "select UserID, duration_seconds from Games where difficulty='" + difficulty.getDifficulty() + "' AND game_won=1 ORDER BY duration_seconds DESC";
        List<TimeRankingRecord> result = new LinkedList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int increment = 1;
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String username = "";
                try {
                    User user = User.getUserByID(userID);
                    username = user.getFirstName() + " " + user.getLastName();
                } catch (UserManagementException e) {
                    System.out.println("Cant get user with this id");
                }
                int seconds = rs.getInt("duration_seconds");
                result.add(new TimeRankingRecord(username, seconds));
                increment++;
                if (increment >= 10) break;
            }
        } catch (SQLException e) {
            System.err.println("Could not read results from database");
        }
        return result;
    }

    @Override
    public TimeRankingRecord getTopTimeUser(Difficulty difficulty) {
        String query = "SELECT U.first_name, U.email_address, G.duration_seconds " +
                "FROM Users U INNER JOIN Games G on U.UserID = G.UserID " +
                "WHERE G.duration_seconds IS NOT NULL AND G.game_won=1" +
                " AND G.difficulty = '"+ difficulty.getDifficulty()+
                "' ORDER BY G.duration_seconds LIMIT 1";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                var name = rs.getString("first_name");
                var email = rs.getString("email_address");
                var time = rs.getInt("duration_seconds");
                return new TimeRankingRecord(name, time, email);
            }
        } catch (SQLException e) {
            System.err.println("Could not grab previous user");
        }
        return null;
    }
}
