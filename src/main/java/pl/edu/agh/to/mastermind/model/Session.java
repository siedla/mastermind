package pl.edu.agh.to.mastermind.model;

import pl.edu.agh.to.mastermind.model.game.Game;
import pl.edu.agh.to.mastermind.model.user.User;

public class Session {
    private static User user;
    private static Game game;

    public Session(){}


    public Game newGame() {
        Session.game = new Game();
        return game;
    }

    public static void setUser(User user) {
        Session.user = user;
    }
}
