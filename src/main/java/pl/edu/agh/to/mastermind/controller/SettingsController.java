package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import pl.edu.agh.to.mastermind.model.game.Difficulty;


public class SettingsController extends Controller{

    @FXML
    private ChoiceBox<Difficulty> difficulty;

    @FXML
    public void initialize() {
        difficulty.getItems().add(Difficulty.EASY);
        difficulty.getItems().add(Difficulty.MEDIUM);
        difficulty.getItems().add(Difficulty.HARD);
    }

    @FXML
    private void onApplyClick(ActionEvent event) throws Exception {
        Difficulty selectedDifficultyLevel = difficulty.getSelectionModel().getSelectedItem();
        this.sceneManager.getSession().setDifficulty(selectedDifficultyLevel);
        sceneManager.switchScene(SceneEnum.MENU);
    }

}
