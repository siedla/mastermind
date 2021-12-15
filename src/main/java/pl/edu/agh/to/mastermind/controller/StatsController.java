package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class StatsController extends Controller{

    @FXML
    private void onReturnClick(ActionEvent event) throws Exception{
        sceneManager.switchScene(SceneEnum.MENU);
    }
}
