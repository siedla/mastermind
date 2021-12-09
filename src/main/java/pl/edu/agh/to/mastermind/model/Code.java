package pl.edu.agh.to.mastermind.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.LinkedList;

public class Code {
    private LinkedList<Colors> colors;

    public Code(LinkedList<Colors> colors) {
        this.colors = colors;
    }

    public LinkedList<Colors> getColors() {
        return colors;
    }

    public GuessResult check(Code guessCode) {
        int guessedCorrectly = 0;
        int guessedInDifferentPlace = 0;
        int t[] = new int[]{0,0,0,0};

        for(int i=0; i<4; i++) {
            if(colors.get(i).equals(guessCode.getColors().get(i))) {
                t[i] = 1;

            }
        }
        for(int i=0; i<4; i++){
            if(t[i] != 1){

                for(int j=0; j<4; j++){
                    if(colors.get(i).equals(guessCode.getColors().get(j)) && t[i]!=-1){
                        t[i] = -1;
                        break;
                    }
                }
            }

        }
        for(int i=0; i<4; i++){
            if(t[i] == 1)
                guessedCorrectly++;
            else if(t[i] == -1)
                guessedInDifferentPlace++;
        }
        return new GuessResult(guessedCorrectly, guessedInDifferentPlace);
    }
}
