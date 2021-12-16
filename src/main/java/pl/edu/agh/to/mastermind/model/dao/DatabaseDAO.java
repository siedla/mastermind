package pl.edu.agh.to.mastermind.model.dao;

import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.game.Difficulty;
import pl.edu.agh.to.mastermind.model.game.Game;
import pl.edu.agh.to.mastermind.model.user.User;

import javax.swing.plaf.nimbus.State;
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
        final var result = finalResult==0?0:1;
        String query = "insert into Games (UserID, number_of_rounds, game_won, difficulty)"
                + "values ('" + userID + "', '" + numberOfRounds + "', '" + result + "', '" + difficulty + "')";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Could not store result in database");
        }
    }

    public String getRanking(Difficulty difficulty){
        String query = "select * from Games where difficulty='"+difficulty.getDifficulty()+"' ORDER BY game_won DESC";
        String result="";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int increment = 1;
            while(rs.next()){
                    int userID = rs.getInt("UserID");
                    String username="";
                    String nor = rs.getString("game_won");
                    result += increment + " UserID: " + userID + " First name: "+username+" Wins: " + nor + "\n";
                    increment++;
                    if(increment>=10) break;
            }
        } catch (SQLException e) {
            System.err.println("Could not read results from database");
        }
        return result;
    }
}
