package pl.edu.agh.to.mastermind.model.dao;

import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.game.Difficulty;
import pl.edu.agh.to.mastermind.model.user.RankingRecord;
import pl.edu.agh.to.mastermind.model.user.TimeRankingRecord;
import pl.edu.agh.to.mastermind.model.user.User;

import java.util.List;

public interface DAO {
    void storeGameResult(Session gameSession, int finalResult);
    void storeGameResult(Session gameSession, int finalResult, long time_ms);
    List<RankingRecord> getRanking(Difficulty difficulty);
    List<TimeRankingRecord> getTimeRanking(Difficulty difficulty);
    TimeRankingRecord getTopTimeUser(Difficulty difficulty);
}
