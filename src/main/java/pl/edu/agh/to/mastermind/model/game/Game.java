package pl.edu.agh.to.mastermind.model.game;

import java.util.LinkedList;
import java.util.Random;

public class Game {
    public static final int maxNumberOfRounds = 10;

    private int currentRound = 1;
    private LinkedList<Round> rounds = new LinkedList<>();
    private Code code;
    private Difficulty difficulty;
    private Random random = new Random();

    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
        generateCode();
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void nextRound(Round round){
        rounds.add(round);
        currentRound++;
    }

    private void generateCode() {
        LinkedList<Colors> selectedColors = new LinkedList<>();
        int colors = 4;

        if(difficulty.equals(Difficulty.HARD)){
            colors = 6;
        }
        if(difficulty.equals(Difficulty.MEDIUM)){
            colors = 5;
        }

        for(int i=0; i<4; i++) {
            int num = random.nextInt(colors);
            selectedColors.add(Colors.values()[num]);
            System.out.println(num+ "   "+Colors.values()[num]);
        }
        this.code = new Code(selectedColors);
    }

    public Code getCode() {
        return code;
    }

}
