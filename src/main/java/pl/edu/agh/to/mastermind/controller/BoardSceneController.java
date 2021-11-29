package pl.edu.agh.to.mastermind.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

public class BoardSceneController {
    public BoardSceneController() {}

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

    @FXML
    private void onEndRoundButtonClick(ActionEvent event){
        System.out.println("CLICK! (end round)");
    }

    @FXML
    private void onNewGameButtonClick(ActionEvent event){
        System.out.println("CLICK! (new game)");
    }

}
