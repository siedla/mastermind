package pl.edu.agh.to.mastermind.model;

import pl.edu.agh.to.mastermind.model.game.Game;
import pl.edu.agh.to.mastermind.model.user.User;

public class Session {
    private  User user;
    private static Game game;

    public Session(){}


    public Game newGame() {
        Session.game = new Game();
        return game;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
