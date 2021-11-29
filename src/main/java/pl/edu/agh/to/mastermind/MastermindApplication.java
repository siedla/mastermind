package pl.edu.agh.to.mastermind;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class MastermindApplication extends Application {
    private static final Logger log = Logger.getLogger(MastermindApplication.class.toString());

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MASTERMIND by Kwadratowe Kafelki");


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("boardScene.fxml"));

        Scene boardScene = new Scene(fxmlLoader.load());

        primaryStage.setScene(boardScene);
        primaryStage.show();
    }
}