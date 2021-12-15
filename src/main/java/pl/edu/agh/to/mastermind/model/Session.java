package pl.edu.agh.to.mastermind.model;

import pl.edu.agh.to.mastermind.model.game.Difficulty;
import pl.edu.agh.to.mastermind.model.game.Game;
import pl.edu.agh.to.mastermind.model.user.User;

public class Session {
    private User user;
    private Game game;
    private Difficulty difficulty;

    public Session(){
        this.difficulty = Difficulty.EASY;
    }


    public Game newGame() {
        this.game = new Game(this.difficulty);
        return game;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }
}
