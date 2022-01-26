package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.to.mastermind.mail.EmailSender;
import pl.edu.agh.to.mastermind.model.user.User;
import pl.edu.agh.to.mastermind.model.user.UserManagementException;

import java.lang.reflect.Field;
import java.util.Objects;

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
    private void onRegisterClick(ActionEvent event) throws Exception {
        String password = passwordField.getText();

        String email = emailField.getText();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (password.equals("")){
            alert.setContentText("Password not specified.");
            alert.show();
        } else if (email.equals("")){
            alert.setContentText("Email not specified.");
            alert.show();
        } else if (firstname.equals("")){
            alert.setContentText("Firstname not specified.");
            alert.show();
        } else if (lastname.equals("")){
            alert.setContentText("Lastname not specified.");
            alert.show();
        } else if (!firstname.matches("^([a-z]|[A-Z])+$")){
            alert.setContentText("Invalid firstname.");
            alert.show();
        } else if (!lastname.matches("^([a-z]|[A-Z])+$")){
            alert.setContentText("Invalid lastname.");
            alert.show();
        } else if (!email.matches("^([a-z]|[A-Z]|\\.)+@([a-z]|[A-Z]|\\.)+$")){
            alert.setContentText("Invalid email.");
            alert.show();
        }
        else {
            User user = null;
            try {
                user = User.register(firstname, lastname, email, password);
            } catch (UserManagementException e){
                alert.setContentText(e.toString());
            }
            if (user != null){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Registered successfully. You may log in now.");

                String subject = "Rejestracja";
                String content = "Witaj, "+firstname+" cieszymy się, że dołączyłeś do grona graczy mastermind.";
                EmailSender.sendEmail(email, subject, content);

                sceneManager.switchScene(SceneEnum.LOGIN);
            }
            alert.show();
        }

    }

    @FXML
    private void onReturnClick(ActionEvent event) throws Exception {
        sceneManager.switchScene(SceneEnum.LOGIN);
    }

    @FXML
    private void onKeyPressed(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER){
            onRegisterClick(new ActionEvent());
        }
    }
}
