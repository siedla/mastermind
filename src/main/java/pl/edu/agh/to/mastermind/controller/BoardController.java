package pl.edu.agh.to.mastermind.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.game.*;

import java.util.LinkedList;

public class BoardController extends Controller{

    private Game game;

    public BoardController() {
    }

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label gameTimeLabel;

    @FXML
    private Label roundLabel;

    @FXML
    public Button endRoundButton;

    @FXML
    public Button newGameButton;

    @FXML private Circle c0;
    @FXML private Circle c1;
    @FXML private Circle c2;
    @FXML private Circle c3;
    @FXML private Circle c4;
    @FXML private Circle c5;

    @FXML private AnchorPane[] attempt;
    @FXML private AnchorPane[] guess;

    @FXML private AnchorPane try0;
    @FXML private AnchorPane try1;
    @FXML private AnchorPane try2;
    @FXML private AnchorPane try3;
    @FXML private AnchorPane try4;
    @FXML private AnchorPane try5;
    @FXML private AnchorPane try6;
    @FXML private AnchorPane try7;
    @FXML private AnchorPane try8;
    @FXML private AnchorPane try9;

    @FXML private AnchorPane guess0;
    @FXML private AnchorPane guess1;
    @FXML private AnchorPane guess2;
    @FXML private AnchorPane guess3;
    @FXML private AnchorPane guess4;
    @FXML private AnchorPane guess5;
    @FXML private AnchorPane guess6;
    @FXML private AnchorPane guess7;
    @FXML private AnchorPane guess8;
    @FXML private AnchorPane guess9;


    private Paint selectedColor;

    @FXML
    private void onMenuClick(ActionEvent event) throws Exception{
        sceneManager.switchScene(SceneEnum.MENU);
    }

    @FXML
    private void onEndRoundButtonClick(ActionEvent event) throws Exception {

        boolean allSelected = true;
        System.out.println(game.getCurrentRound()-1);
        for(int i=0; i<4; i++){
            if( ((Circle) attempt[game.getCurrentRound()-1].getChildren().get(i)).getFill() == Color.LIGHTGRAY){
                allSelected = false;
            }
        }

        if(!allSelected){
            Alert dialogBox = new Alert(Alert.AlertType.ERROR);
            dialogBox.setTitle("Error");
            dialogBox.setHeaderText("Selection error");
            dialogBox.setContentText("Please select a color for every circle!");
            dialogBox.showAndWait();
        }
        else{
            LinkedList<Colors> guessedCode= new LinkedList<>();
            for(int i=0; i<4; i++){
                Paint c = ((Circle) attempt[game.getCurrentRound()-1].getChildren().get(i)).getFill();
                guessedCode.add(Colors.valueOf(c));

            }
            Code code = new Code(guessedCode);

            GuessResult CurrentGuess = game.getCode().check(code);

            for(int i=0; i<CurrentGuess.getGuessedCorrectly(); i++){
                ((Circle) guess[game.getCurrentRound()-1].getChildren().get(i)).setFill(Colors.BLACK.getValue());
            }
            for(int i=CurrentGuess.getGuessedCorrectly(); i<CurrentGuess.getGuessedInDifferentPlace()+CurrentGuess.getGuessedCorrectly(); i++){
                ((Circle) guess[game.getCurrentRound()-1].getChildren().get(i)).setFill(Colors.WHITE.getValue());
            }

            if (CurrentGuess.getGuessedCorrectly() == 4) {
                executeGameWon();
            }

            else if (game.getCurrentRound() == 10 && CurrentGuess.getGuessedCorrectly() != 4) {
                executeGameLost();
            }

            else game.nextRound(new Round(code, CurrentGuess));
        }
    }

    private void executeGameLost() throws Exception {
        Alert dialogBox = new Alert(Alert.AlertType.INFORMATION);
        dialogBox.setTitle("Game over!");
        dialogBox.setHeaderText("You lose!");
        dialogBox.setContentText("You failed to guess the code. Try again!");
        dialogBox.showAndWait();
        endRoundButton.setVisible(false);
        sceneManager.switchScene(SceneEnum.MENU);
    }

    private void executeGameWon() throws Exception {
        Alert dialogBox = new Alert(Alert.AlertType.INFORMATION);
        dialogBox.setTitle("Congratulations! :)");
        dialogBox.setHeaderText("You win!");
        dialogBox.setContentText("You managed to win in round "+game.getCurrentRound());

        dialogBox.showAndWait();
        endRoundButton.setVisible(false);
        sceneManager.switchScene(SceneEnum.MENU);
    }

    private void cleanScreenState(){
        for(int i=0; i<10; i++){
            for(int j=0; j<attempt[i].getChildren().size(); j++){
                ((Circle)attempt[i].getChildren().get(j)).setFill(Color.LIGHTGRAY);
                ((Circle)guess[i].getChildren().get(j)).setFill(Color.DARKGRAY);
            }
        }
    }

    @FXML
    private void onNewGameButtonClick(ActionEvent event){
        startNewGame();
    }



    @FXML
    public void initialize() {
        c0.setFill(Colors.BLUE.getValue());
        c1.setFill(Colors.YELLOW.getValue());
        c2.setFill(Colors.GREEN.getValue());
        c3.setFill(Colors.RED.getValue());
        c4.setFill(Colors.ORANGE.getValue());
        c5.setFill(Colors.PURPLE.getValue());

        selectListener(c0, 0);
        selectListener(c1, 1);
        selectListener(c2, 2);
        selectListener(c3, 3);
        selectListener(c4, 4);
        selectListener(c5, 5);

        attempt = new AnchorPane[]{try0, try1, try2, try3, try4, try5, try6, try7, try8, try9};
        guess = new AnchorPane[]{guess0, guess1, guess2, guess3, guess4, guess5, guess6, guess7,
                guess8, guess9};

        for(int i=0; i<10; i++){
            for(int j=0; j<attempt[i].getChildren().size(); j++){
                ((Circle)attempt[i].getChildren().get(j)).setFill(Color.LIGHTGRAY);
                ((Circle)guess[i].getChildren().get(j)).setFill(Color.DARKGRAY);
                markDetection(attempt[i].getChildren().get(j), i+1);
            }
        }

    }

    public void startNewGame() {
        cleanScreenState();
        var session = sceneManager.getSession();
        this.game = session.newGame();
        System.out.println(session.getDifficulty());
        if(session.getDifficulty().equals(Difficulty.EASY)){
            c4.setVisible(false);
            c5.setVisible(false);
        }
        else if(session.getDifficulty().equals(Difficulty.MEDIUM)){
            c5.setVisible(false);
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void markDetection(Node n, int i) {
        n.setOnMouseClicked(event -> {
            if(i == game.getCurrentRound() && selectedColor!=null){
                ((Circle) n).setFill(selectedColor);

            }

        });

    }

    private void selectListener(Node n, int i) {
        n.setOnMouseClicked(event -> {

            if(selectedColor != null){
                c0.setEffect(null);
                c1.setEffect(null);
                c2.setEffect(null);
                c3.setEffect(null);
                c4.setEffect(null);
                c5.setEffect(null);
            }
            if(i==0) {
                selectedColor = c0.getFill();
                c0.setEffect(new DropShadow(40, Color.BLACK));
            }
            if(i==1) {
                selectedColor = c1.getFill();
                c1.setEffect(new DropShadow(40, Color.BLACK));
            }
            if(i==2) {
                selectedColor = c2.getFill();
                c2.setEffect(new DropShadow(40, Color.BLACK));
            }
            if(i==3) {
                selectedColor = c3.getFill();
                c3.setEffect(new DropShadow(40, Color.BLACK));
            }
            if(i==4) {
                selectedColor = c4.getFill();
                c4.setEffect(new DropShadow(40, Color.BLACK));
            }
            if(i==5) {
                selectedColor = c5.getFill();
                c5.setEffect(new DropShadow(40, Color.BLACK));
            }
        });

    }
}
