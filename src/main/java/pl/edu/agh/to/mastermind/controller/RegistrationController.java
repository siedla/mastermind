package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController extends Controller{

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private void onSignInClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.BOARD);
    }

    @FXML
    private void onReturnClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.LOGIN);
    }
}
