package pl.edu.agh.to.mastermind;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.to.mastermind.controller.BoardController;
import pl.edu.agh.to.mastermind.controller.SceneEnum;
import pl.edu.agh.to.mastermind.controller.SceneManager;
import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.game.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class MastermindApplication extends Application {
    private static final Logger log = Logger.getLogger(MastermindApplication.class.toString());

    private SceneManager sceneManager;


    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pmakarew","pmakarew","tMiKdJD9H9zKZbTn");
            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from emp");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.sceneManager = new SceneManager(stage);
        BoardController boardController = (BoardController) this.sceneManager.getController(SceneEnum.BOARD);
        boardController.setSession(new Session());
        boardController.setGame(new Game());
        System.out.println(stage.getScene());
        stage.show();
    }
}