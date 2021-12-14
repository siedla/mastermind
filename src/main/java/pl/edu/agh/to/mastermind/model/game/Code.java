package pl.edu.agh.to.mastermind.model.game;

import java.util.LinkedList;
import java.util.List;

public class Code {

    private List<Colors> colors;

    public Code() {
        this.colors = new LinkedList<Colors>();
    }

    public Code(List<Colors> colors) {
        this.colors = colors;
    }

    public List<Colors> getColors() {
        return colors;
    }

    public GuessResult check(Code guessCode) {
        if (this.colors.size() != guessCode.colors.size())
            return new GuessResult(0, 0);

        var slotUsed = new boolean[colors.size()];
        var otherColors = guessCode.getColors();

        int guessedCorrectly = 0;
        int guessedInDifferentPlace = 0;

        for (int i = 0; i < otherColors.size(); ++i) {
            if (otherColors.get(i) == colors.get(i)) {
                guessedCorrectly += 1;
                slotUsed[i] = true;
            }
        }

        for (Colors otherColor : otherColors) {
            for (int j = 0; j < colors.size(); ++j) {
                if (otherColor == colors.get(j) && !slotUsed[j]) {
                    guessedInDifferentPlace += 1;
                    slotUsed[j] = true;
                    break;
                }
            }
        }

        return new GuessResult(guessedCorrectly, guessedInDifferentPlace);
    }
}
