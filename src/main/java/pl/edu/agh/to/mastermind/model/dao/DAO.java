package pl.edu.agh.to.mastermind.model.dao;

import pl.edu.agh.to.mastermind.model.Session;

public interface DAO {
    void storeGameResult(Session gameSession, int finalResult);
}
