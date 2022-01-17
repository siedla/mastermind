package pl.edu.agh.to.mastermind.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("kwadratowekafelki@gmail.com", "rzjbpehnkwkbwefk");
    }
}
