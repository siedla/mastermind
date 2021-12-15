package pl.edu.agh.to.mastermind.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.to.mastermind.model.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SceneManager {
    private final Stage stage;
    private final Map<SceneEnum, Scene> scenes = new HashMap<>();
    private final Map<SceneEnum, Controller> controllers = new HashMap<>();

    private Session session;

    public void setSession(Session s)
    {
        this.session = s;
    }

    public Session getSession()
    {
        return this.session;
    }

    public SceneManager(Stage stage) throws Exception {
        this.stage = stage;
        this.loadScenes();
        this.switchScene(SceneEnum.LOGIN);
    }

    private void loadScenes() throws IOException {
        for (SceneEnum sceneType: SceneEnum.values()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource(sceneType.getFxmlFileName()));
            Scene scene = new Scene(fxmlLoader.load());
            scenes.put(sceneType, scene);
            Controller controller = fxmlLoader.getController();
            controller.setSceneManager(this);
            controllers.put(sceneType, controller);
            System.out.println(sceneType);
            System.out.println(sceneType.getFxmlFileName());
        }
    }

    public void switchScene(SceneEnum sceneEnum) throws Exception {
        stage.setScene(scenes.get(sceneEnum));
    }

    public Controller getController(SceneEnum sceneEnum){
        return controllers.get(sceneEnum);
    }
}
