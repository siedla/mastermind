package pl.edu.agh.to.mastermind.mail;

import javafx.concurrent.Task;

import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public static void sendEmail(String recipient, String subject, String content) {

        try{
            Task<Void> task = new Task<>() {
                @Override protected Void call() throws Exception {
                    MimeMessage message = MailMessagePreparer.prepareTextMessageObject(recipient, subject, content);
                    Transport.send(message);
                    return null;
                }
            };
            new Thread(task).start();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
