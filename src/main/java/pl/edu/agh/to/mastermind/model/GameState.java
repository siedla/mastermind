package pl.edu.agh.to.mastermind.model;

import java.util.LinkedList;
import java.util.Random;

public class GameState {
    private int currentRound = 1;
    private LinkedList<Round> rounds = new LinkedList<>();
    private Code code;
    private Random random = new Random();

    public GameState() {
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
        for(int i=0; i<4; i++) {
            int num = random.nextInt(4);
            selectedColors.add(Colors.values()[num]);
            System.out.println(num+ "   "+Colors.values()[num]);
        }
        this.code = new Code(selectedColors);
    }

    public Code getCode() {
        return code;
    }

    public void newGame() {
        this.currentRound=1;
        this.rounds.clear();
        generateCode();
    }
}
