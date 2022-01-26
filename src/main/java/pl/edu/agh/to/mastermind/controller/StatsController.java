package pl.edu.agh.to.mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.agh.to.mastermind.model.dao.DAO;
import pl.edu.agh.to.mastermind.model.dao.DatabaseDAO;
import pl.edu.agh.to.mastermind.model.game.Difficulty;
import pl.edu.agh.to.mastermind.model.user.RankingRecord;
import pl.edu.agh.to.mastermind.model.user.TimeRankingRecord;
import pl.edu.agh.to.mastermind.model.user.User;

import java.util.List;

public class StatsController extends Controller{

    @FXML
    private Label rankingLabel;

    @FXML
    private TableView<RankingRecord> rankingTable;

    @FXML
    private TableColumn<RankingRecord, String> usernameCol;

    @FXML
    private TableColumn<RankingRecord, Integer> winsCol;

    @FXML
    private TableColumn<RankingRecord, Integer> roundsCol;

    @FXML
    private TableView<TimeRankingRecord> rankingTimeTable;

    @FXML
    private TableColumn<TimeRankingRecord, String> usernameCol2;

    @FXML
    private TableColumn<TimeRankingRecord, Integer> timeCol;



    private List<RankingRecord> records;
    private List<TimeRankingRecord> timeRecords;


    private DAO gameResultStorage= new DatabaseDAO();

    @FXML
    private ChoiceBox<Difficulty> difficulty;

    @FXML
    public void initialize(){
        difficulty.getItems().add(Difficulty.EASY);
        difficulty.getItems().add(Difficulty.MEDIUM);
        difficulty.getItems().add(Difficulty.HARD);

        usernameCol.setCellValueFactory(new PropertyValueFactory<RankingRecord, String>("username"));
        winsCol.setCellValueFactory(new PropertyValueFactory<RankingRecord, Integer>("wins"));
        roundsCol.setCellValueFactory(new PropertyValueFactory<RankingRecord, Integer>("rounds"));
        usernameCol2.setCellValueFactory(new PropertyValueFactory<TimeRankingRecord, String>("username"));
        timeCol.setCellValueFactory(new PropertyValueFactory<TimeRankingRecord, Integer>("seconds"));

        this.records = gameResultStorage.getRanking(Difficulty.EASY);
        this.timeRecords = gameResultStorage.getTimeRanking(Difficulty.EASY);
        rankingTable.getItems().setAll(this.records);
        rankingTimeTable.getItems().setAll(this.timeRecords);
    }

    @FXML
    private void onApplyClick(ActionEvent event) throws Exception {
        this.records = gameResultStorage.getRanking(difficulty.getSelectionModel().getSelectedItem());
        rankingTable.getItems().setAll(this.records);
        this.timeRecords = gameResultStorage.getTimeRanking(difficulty.getSelectionModel().getSelectedItem());
        rankingTimeTable.getItems().setAll(this.timeRecords);
    }

    @FXML
    private void onReturnClick(ActionEvent event) throws Exception{
        sceneManager.switchScene(SceneEnum.MENU);
    }
}
