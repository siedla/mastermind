package pl.edu.agh.to.mastermind.model;

import pl.edu.agh.to.mastermind.model.game.Game;
import pl.edu.agh.to.mastermind.model.user.User;

public class Session {
    private User user;
    private Game game;

    public Session(){}

    public void login(String email, String password){

    }

    public void register(String email, String password){

    }

    public Game newGame() {
        this.game = new Game();
        return this.game;
    }
}
