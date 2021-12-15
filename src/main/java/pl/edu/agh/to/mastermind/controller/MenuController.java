package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController extends Controller{
    @FXML
    private void onNewGameClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.BOARD);
    }
    @FXML
    private void onSettingsClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.SETTINGS);
    }
    @FXML
    private void onStatsClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.STATISTICS);
    }
    @FXML
    private void onLogOutClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.LOGIN);
    }

}
