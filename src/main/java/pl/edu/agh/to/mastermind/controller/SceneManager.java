package pl.edu.agh.to.mastermind.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private final Stage stage;
    private final Map<SceneEnum, Scene> scenes = new HashMap<>();
    private final Map<SceneEnum, Controller> controllers = new HashMap<>();

    public SceneManager(Stage stage) throws Exception {
        this.stage = stage;
        this.loadScenes();
        this.switchScene(SceneEnum.BOARD);
    }

    private void loadScenes() throws IOException {
        for (SceneEnum sceneType: SceneEnum.values()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("boardScene.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scenes.put(sceneType, scene);
            Controller controller = fxmlLoader.getController();
            controller.setSceneManager(this);
            controllers.put(sceneType, controller);
        }
    }

    public void switchScene(SceneEnum sceneEnum) throws Exception {
        stage.setScene(scenes.get(sceneEnum));
    }

    public Controller getController(SceneEnum sceneEnum){
        return controllers.get(sceneEnum);
    }
}
