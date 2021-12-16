package pl.edu.agh.to.mastermind.model.dao;

import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.game.Difficulty;

public interface DAO {
    void storeGameResult(Session gameSession, int finalResult);
    String getRanking(Difficulty difficulty);
}
