package pl.edu.agh.to.mastermind.model;

import pl.edu.agh.to.mastermind.model.game.Game;

public class Session {
    private Player player;
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
