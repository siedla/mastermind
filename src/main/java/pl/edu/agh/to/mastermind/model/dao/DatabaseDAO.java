package pl.edu.agh.to.mastermind.model.dao;

import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.game.Game;

import java.sql.*;

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
        if (connection == null) {
            return;
        }
        final var userID = gameSession.getUser().getId();
        final var numberOfRounds = gameSession.getGame().getCurrentRound();
        final var difficulty = gameSession.getDifficulty();
        String query = "insert into Games (UserID, number_of_rounds, game_won, difficulty)"
                + "values ('" + userID + "', '" + numberOfRounds + "', '" + finalResult + "', '" + difficulty + "')";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Could not store result in database");
        }
    }
}
