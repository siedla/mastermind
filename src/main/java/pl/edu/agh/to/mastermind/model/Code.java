package pl.edu.agh.to.mastermind.model;

import java.util.LinkedList;

public class Code {
    private LinkedList<Color> colors = new LinkedList<>();

    public LinkedList<Color> getColors() {
        return colors;
    }

    public GuessResult check(Code guessCode) {
        int guessedCorrectly = 0;
        int guessedInDifferentPlace = 0;
        for(int i=0; i<colors.size(); i++) {
            if(colors.get(i) == guessCode.getColors().get(i)) {
                guessedCorrectly++;
            }
            else if(guessCode.getColors().contains(colors.get(i))){
                guessedInDifferentPlace++;
            }
        }
        return new GuessResult(guessedCorrectly, guessedInDifferentPlace);
    }
}
