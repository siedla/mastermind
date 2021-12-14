package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController extends Controller{

    @FXML
    private void onClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.BOARD);
    }
}
