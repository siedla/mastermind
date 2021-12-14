package pl.edu.agh.to.mastermind.controller;

public abstract class Controller {
    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
