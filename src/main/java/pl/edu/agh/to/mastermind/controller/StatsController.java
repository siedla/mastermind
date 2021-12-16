package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import pl.edu.agh.to.mastermind.model.dao.DAO;
import pl.edu.agh.to.mastermind.model.dao.DatabaseDAO;
import pl.edu.agh.to.mastermind.model.game.Difficulty;

public class StatsController extends Controller{

    @FXML
    private Label rankingLabel;

    private DAO gameResultStorage= new DatabaseDAO();

    @FXML
    private ChoiceBox<Difficulty> difficulty;

    @FXML
    public void initialize(){
        difficulty.getItems().add(Difficulty.EASY);
        difficulty.getItems().add(Difficulty.MEDIUM);
        difficulty.getItems().add(Difficulty.HARD);
    }

    @FXML
    private void onApplyClick(ActionEvent event) throws Exception {
        String ranking = gameResultStorage.getRanking(difficulty.getSelectionModel().getSelectedItem());
        rankingLabel.setText(ranking);
    }

    @FXML
    private void onReturnClick(ActionEvent event) throws Exception{
        sceneManager.switchScene(SceneEnum.MENU);
    }
}
