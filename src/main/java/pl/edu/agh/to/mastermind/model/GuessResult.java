package pl.edu.agh.to.mastermind.model;

public class GuessResult {
    private int guessedCorrectly;
    private int guessedInDifferentPlace;

    public GuessResult(int guessedCorrectly, int guessedInDifferentPlace) {
        this.guessedCorrectly = guessedCorrectly;
        this.guessedInDifferentPlace = guessedInDifferentPlace;
    }

    public int getGuessedCorrectly() {
        return guessedCorrectly;
    }

    public int getGuessedInDifferentPlace() {
        return guessedInDifferentPlace;
    }
}
