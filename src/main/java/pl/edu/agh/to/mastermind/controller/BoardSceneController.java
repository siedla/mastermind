package pl.edu.agh.to.mastermind.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class BoardSceneController {
    public BoardSceneController() {}

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label gameTimeLabel;

    @FXML
    private Label roundLabel;

    @FXML
    public Button endRoundButton;

    @FXML
    public Button newGameButton;

    @FXML private Circle c0;
    @FXML private Circle c1;
    @FXML private Circle c2;
    @FXML private Circle c3;
    @FXML private Circle c4;

    @FXML private AnchorPane try0;
    @FXML private AnchorPane try1;
    @FXML private AnchorPane try2;
    @FXML private AnchorPane try3;
    @FXML private AnchorPane try4;
    @FXML private AnchorPane try5;
    @FXML private AnchorPane try6;
    @FXML private AnchorPane try7;
    @FXML private AnchorPane try8;
    @FXML private AnchorPane try9;



    private AnchorPane[] tryAnchorPanes;
    private Paint selectedColor;

    @FXML
    private void onEndRoundButtonClick(ActionEvent event){
        System.out.println("CLICK! (end round)");
    }

    @FXML
    private void onNewGameButtonClick(ActionEvent event){
        System.out.println("CLICK! (new game)");
    }

    @FXML
    public void initialize() {
        tryAnchorPanes = new AnchorPane[] {try0, try1};

        selectListener(c0, 0);
        selectListener(c1, 1);
        selectListener(c2, 2);
        selectListener(c3, 3);

        for (int i = 0; i < try0.getChildren().size(); i++) {
            ((Circle)try0.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try0.getChildren().get(i));
        }
        for (int i = 0; i < try1.getChildren().size(); i++) {
            ((Circle)try1.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try1.getChildren().get(i));
        }
        for (int i = 0; i < try2.getChildren().size(); i++) {
            ((Circle)try2.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try2.getChildren().get(i));
        }
        for (int i = 0; i < try3.getChildren().size(); i++) {
            ((Circle)try3.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try3.getChildren().get(i));
        }
        for (int i = 0; i < try4.getChildren().size(); i++) {
            ((Circle)try4.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try4.getChildren().get(i));
        }
        for (int i = 0; i < try5.getChildren().size(); i++) {
            ((Circle)try5.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try5.getChildren().get(i));
        }
        for (int i = 0; i < try6.getChildren().size(); i++) {
            ((Circle)try6.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try6.getChildren().get(i));
        }
        for (int i = 0; i < try7.getChildren().size(); i++) {
            ((Circle)try7.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try7.getChildren().get(i));
        }
        for (int i = 0; i < try8.getChildren().size(); i++) {
            ((Circle)try8.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try8.getChildren().get(i));
        }
        for (int i = 0; i < try9.getChildren().size(); i++) {
            ((Circle)try9.getChildren().get(i)).setFill(Color.LIGHTGRAY);
            markDetection(try9.getChildren().get(i));
        }

    }

    private void markDetection(Node n) {

        n.setOnMouseClicked(event -> {
            ((Circle) n).setFill(selectedColor);
        });

    }

    private void selectListener(Node n, int i) {

        n.setOnMouseClicked(event -> {
            if(i==0) {
                selectedColor = c0.getFill();
            }
            if(i==1) {
                selectedColor = c1.getFill();
            }
            if(i==2) {
                selectedColor = c2.getFill();
            }
            if(i==3) {
                selectedColor = c3.getFill();
            }

        });

    }
}
