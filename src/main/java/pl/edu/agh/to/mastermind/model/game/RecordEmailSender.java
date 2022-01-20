package pl.edu.agh.to.mastermind.model.game;

import pl.edu.agh.to.mastermind.mail.EmailSender;
import pl.edu.agh.to.mastermind.model.Session;
import pl.edu.agh.to.mastermind.model.dao.DAO;
import pl.edu.agh.to.mastermind.model.user.TimeRankingRecord;
import pl.edu.agh.to.mastermind.model.user.User;

public class RecordEmailSender {
    private DAO dao;
    private Session session;

    public RecordEmailSender(DAO gameResultStorage, Session session) {
        this.dao = gameResultStorage;
        this.session = session;
    }


    public void maybeSendWithTime(long thisTime) {
        User u = session.getUser();
        var timeS = thisTime/1000;
        var possiblePreviousBestUser = dao.getTopTimeUser(session.getDifficulty());
        if (possiblePreviousBestUser != null && possiblePreviousBestUser.getSeconds() > timeS)
            sendEmailRecordLost(possiblePreviousBestUser);
    }

    private void sendEmailRecordLost(TimeRankingRecord previousRecordHolder) {
        String content = "Dear " + previousRecordHolder.getUsername() +
                "\nWe regret to inform you that your record on difficulty " +
                session.getDifficulty().getDifficulty() + " with time of " +
                previousRecordHolder.getSeconds() + "s has been beaten.\n" +
                "We invite you to check the record table to find out who beat you, " +
                "and play more to regain your place!\n" +
                "Regards,\nTeam Kwadratowe Kafelki";
        try {
            EmailSender.sendEmail(previousRecordHolder.getEmail(), "Mastermind mastery", content);
        } catch (RuntimeException e){
            System.err.println("Could not send email to previous recordholder");
        }
    }
}
