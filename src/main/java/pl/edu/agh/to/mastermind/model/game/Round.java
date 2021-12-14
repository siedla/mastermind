package pl.edu.agh.to.mastermind.model.game;

public class Round {
    private Code code;
    private GuessResult result;

    public Round(Code code, GuessResult result) {
        this.code = code;
        this.result = result;
    }
}
