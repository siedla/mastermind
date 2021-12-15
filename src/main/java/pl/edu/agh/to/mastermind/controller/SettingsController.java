package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SettingsController extends Controller{
    @FXML
    private void onApplyClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.MENU);
    }
}
