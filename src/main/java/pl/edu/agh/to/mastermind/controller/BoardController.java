package pl.edu.agh.to.mastermind.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import pl.edu.agh.to.mastermind.model.dao.DAO;
import pl.edu.agh.to.mastermind.model.dao.DatabaseDAO;
import pl.edu.agh.to.mastermind.model.game.*;

import java.util.LinkedList;

public class BoardController extends Controller {
    private Game game;
    private DAO gameResultStorage = new DatabaseDAO();

    final int numOfRows = 10;


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

    Circle[] colorSelectionCircles;

    @FXML
    private Circle c0;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;
    @FXML
    private Circle c4;
    @FXML
    private Circle c5;

    @FXML
    private VBox attempts;
    @FXML
    public VBox guesses;

    @FXML
    private AnchorPane[] attemptPanes;
    @FXML
    private AnchorPane[] guessPanes;


    private Paint selectedColor;

    @FXML
    private void onMenuClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.MENU);
    }

    @FXML
    private void onEndRoundButtonClick(ActionEvent event) throws Exception {

        boolean allSelected = true;
        System.out.println(game.getCurrentRound() - 1);
        for (int i = 0; i < 4; i++) {
            if (((Circle) attemptPanes[game.getCurrentRound() - 1].getChildren().get(i)).getFill() == Color.LIGHTGRAY) {
                allSelected = false;
            }
        }

        if (!allSelected) {
            displaySelectionAlert();
        } else {
            serveFinishedRound();
        }
    }

    private void serveFinishedRound() throws Exception {
        LinkedList<Colors> guessedCode = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            Paint c = ((Circle) attemptPanes[game.getCurrentRound() - 1].getChildren().get(i)).getFill();
            guessedCode.add(Colors.valueOf(c));

        }
        Code code = new Code(guessedCode);

        GuessResult currentGuess = game.getCode().check(code);

        paintGuessResultCircles(currentGuess);

        if (currentGuess.getGuessedCorrectly() == 4) {
            executeGameWon();
        } else if (game.getCurrentRound() == Game.maxNumberOfRounds && currentGuess.getGuessedCorrectly() != 4) {
            executeGameLost();
        } else {
            game.nextRound(new Round(code, currentGuess));
            roundLabel.setText(String.valueOf(game.getCurrentRound()));
        }
    }

    private void paintGuessResultCircles(GuessResult currentGuess) {
        for (int i = 0; i < currentGuess.getGuessedCorrectly(); i++) {
            ((Circle) guessPanes[game.getCurrentRound() - 1].getChildren().get(i)).setFill(Colors.BLACK.getValue());
        }
        for (int i = currentGuess.getGuessedCorrectly(); i < currentGuess.getGuessedInDifferentPlace() + currentGuess.getGuessedCorrectly(); i++) {
            ((Circle) guessPanes[game.getCurrentRound() - 1].getChildren().get(i)).setFill(Colors.WHITE.getValue());
        }
    }

    private void displaySelectionAlert() {
        Alert dialogBox = new Alert(Alert.AlertType.ERROR);
        dialogBox.setTitle("Error");
        dialogBox.setHeaderText("Selection error");
        dialogBox.setContentText("Please select a color for every circle!");
        dialogBox.showAndWait();
    }

    private void executeGameLost() throws Exception {
        Alert dialogBox = new Alert(Alert.AlertType.INFORMATION);
        dialogBox.setTitle("Game over!");
        dialogBox.setHeaderText("You lose!");
        dialogBox.setContentText("You failed to guess the code. Try again!");
        gameResultStorage.storeGameResult(sceneManager.getSession(), 0);
        dialogBox.showAndWait();
        endRoundButton.setVisible(false);
        sceneManager.switchScene(SceneEnum.MENU);
    }

    private void executeGameWon() throws Exception {
        Alert dialogBox = new Alert(Alert.AlertType.INFORMATION);
        dialogBox.setTitle("Congratulations! :)");
        dialogBox.setHeaderText("You win!");
        dialogBox.setContentText("You managed to win in round " + game.getCurrentRound());

        gameResultStorage.storeGameResult(sceneManager.getSession(), 1);
        dialogBox.showAndWait();
        endRoundButton.setVisible(false);
        sceneManager.switchScene(SceneEnum.MENU);
    }

    private void cleanScreenState() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < attemptPanes[i].getChildren().size(); j++) {
                ((Circle) attemptPanes[i].getChildren().get(j)).setFill(Color.LIGHTGRAY);
                ((Circle) guessPanes[i].getChildren().get(j)).setFill(Color.DARKGRAY);
            }
        }
    }

    @FXML
    private void onNewGameButtonClick(ActionEvent event) {
        startNewGame();
    }


    @FXML
    public void initialize() {
        prepareColorSelectionCircles();

        prepareAttempts();
        prepareGuesses();

        paintAttepmtsAndGuessCircles();

        for (var row : attemptPanes) {
            attempts.getChildren().add(row);
        }
        for (var row : guessPanes) {
            guesses.getChildren().add(row);
        }

    }

    private void paintAttepmtsAndGuessCircles() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < attemptPanes[i].getChildren().size(); j++) {
                ((Circle) attemptPanes[i].getChildren().get(j)).setFill(Color.LIGHTGRAY);
                ((Circle) guessPanes[i].getChildren().get(j)).setFill(Color.DARKGRAY);
                markDetection(attemptPanes[i].getChildren().get(j), i + 1);
            }
        }
    }

    private void prepareColorSelectionCircles() {
        c0.setFill(Colors.BLUE.getValue());
        c1.setFill(Colors.YELLOW.getValue());
        c2.setFill(Colors.GREEN.getValue());
        c3.setFill(Colors.RED.getValue());
        c4.setFill(Colors.ORANGE.getValue());
        c5.setFill(Colors.PURPLE.getValue());
        colorSelectionCircles = new Circle[]{c0, c1, c2, c3, c4, c5};

        for (int i = 0; i < 6; i++) {
            selectListener(colorSelectionCircles[i], i);
        }
    }

    private void prepareGuesses() {
        guessPanes = new AnchorPane[numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            guessPanes[i] = new AnchorPane();
            var row = guessPanes[i];
            row.setPrefWidth(200);
            row.setPrefHeight(200);
            addGuessCircles(row);
        }
    }

    private void addGuessCircles(AnchorPane row) {
        int baseX = 24;
        final int numOfCircles = 4;
        for (int i = 0; i < numOfCircles; i++) {
            Circle c = new Circle();
            c.setLayoutX(baseX);
            c.setLayoutY(45);
            c.setRadius(10);
            c.setStroke(Color.BLACK);
            c.setStrokeType(StrokeType.INSIDE);
            baseX += 40;
            row.getChildren().add(c);
        }
    }

    private void prepareAttempts() {
        attemptPanes = new AnchorPane[numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            attemptPanes[i] = new AnchorPane();
            var row = attemptPanes[i];
            row.setPrefHeight(200);
            row.setPrefWidth(200);
            addCircles(row);
        }
    }

    private void addCircles(AnchorPane row) {
        int baseX = 50;
        final int numOfCircles = 4;
        for (int i = 0; i < numOfCircles; ++i) {
            Circle c = new Circle();
            c.setLayoutX(baseX);
            c.setLayoutY(46);
            c.setRadius(20);
            c.setFill(Color.WHITE);
            c.setStroke(Color.BLACK);
            c.setStrokeType(StrokeType.INSIDE);
            baseX += 90;
            row.getChildren().add(c);
        }
    }

    public void startNewGame() {
        cleanScreenState();
        endRoundButton.setVisible(true);
        var session = sceneManager.getSession();
        this.game = session.newGame();
        difficultyLabel.setText(session.getDifficulty().toString());
        roundLabel.setText(String.valueOf(game.getCurrentRound()));
        c4.setVisible(true);
        c5.setVisible(true);
        if (session.getDifficulty().equals(Difficulty.EASY)) {
            c4.setVisible(false);
            c5.setVisible(false);
        } else if (session.getDifficulty().equals(Difficulty.MEDIUM)) {
            c5.setVisible(false);
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void markDetection(Node n, int i) {
        n.setOnMouseClicked(event -> {
            if (i == game.getCurrentRound() && selectedColor != null) {
                ((Circle) n).setFill(selectedColor);

            }

        });

    }

    private void selectListener(Node n, int i) {
        n.setOnMouseClicked(event -> {
            for (var c : colorSelectionCircles) {
                c.setEffect(null);
            }
            selectedColor = colorSelectionCircles[i].getFill();
            colorSelectionCircles[i].setEffect(new DropShadow(40, Color.BLACK));
        });

    }
}
