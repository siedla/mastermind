package pl.edu.agh.to.mastermind.mail;

import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public static void sendEmail(String recipient, String subject, String content) {

        try{
            MimeMessage message = MailMessagePreparer.prepareTextMessageObject(recipient, subject, content);
            Transport.send(message);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
