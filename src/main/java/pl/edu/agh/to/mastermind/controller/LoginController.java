package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.user.User;
import pl.edu.agh.to.mastermind.model.user.UserManagementException;

public class LoginController extends Controller{

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void onLogInClick(ActionEvent event) throws Exception {
        String email = emailField.getText();
        String password = passwordField.getText();
        try {
            if (User.checkPasswordMatch(email, password)) {
                User user = User.getUserByEmail(email);

                var session = new Session();
                session.setUser(user);
                sceneManager.setSession(session);
                sceneManager.switchScene(SceneEnum.MENU);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Login succeeded");
                alert.setContentText("You logged in successfully as " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
                alert.show();
            } else {
           throw new UserManagementException("Passwords don't match");
        }}
        catch (UserManagementException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Login failed");
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    @FXML
    private void onSignInClick(ActionEvent event) throws Exception{
        sceneManager.switchScene(SceneEnum.REGISTRATION);
    }
}
