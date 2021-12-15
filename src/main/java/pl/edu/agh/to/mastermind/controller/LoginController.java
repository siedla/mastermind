package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller{

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private void onLogInClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.MENU);
    }

    @FXML
    private void onSignInClick(ActionEvent event) throws Exception{
        sceneManager.switchScene(SceneEnum.REGISTRATION);
    }
}
