package pl.edu.agh.to.mastermind;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.to.mastermind.controller.BoardController;
import pl.edu.agh.to.mastermind.controller.SceneEnum;
import pl.edu.agh.to.mastermind.controller.SceneManager;
import pl.edu.agh.to.mastermind.model.GameState;

import java.util.logging.Logger;

public class MastermindApplication extends Application {
    private static final Logger log = Logger.getLogger(MastermindApplication.class.toString());
    private SceneManager sceneManager;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.sceneManager = new SceneManager(stage);
        GameState state = new GameState();
        BoardController boardController = (BoardController) this.sceneManager.getController(SceneEnum.BOARD);
        boardController.setGameStateModel(state);
        stage.show();
    }
}