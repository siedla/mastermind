package pl.edu.agh.to.mastermind.controller;

public enum SceneEnum {
    BOARD("boardScene.fxml"),
    STATISTICS("statsScene.fxml"),
    LOGIN("loginScene2.fxml"),
    REGISTRATION("registerScene.fxml"),
    MENU("menuScene.fxml"),
    SETTINGS("settingsScene.fxml")
    ;

    private final String fxmlFileName;
    SceneEnum(String fxmlFileName){
        this.fxmlFileName = fxmlFileName;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }
}
