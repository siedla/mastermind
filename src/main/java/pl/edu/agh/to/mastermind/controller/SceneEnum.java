package pl.edu.agh.to.mastermind.controller;

public enum SceneEnum {
    BOARD("boardScene.fxml"),
    //STATISTICS("statisticsScene.fxml"),
    LOGIN("loginScene.fxml")
    //MENU("menuScene.fxml")
    ;

    private final String fxmlFileName;
    SceneEnum(String fxmlFileName){
        this.fxmlFileName = fxmlFileName;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }
}
