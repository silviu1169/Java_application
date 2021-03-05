package service;

import gui.MessageAlert;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class SendEmail extends Thread {
    private final Properties properties;
    private final String host;
    private String subject;
    private String message;
    private List<String> recipients;
    private String sender;

    public SendEmail(String message, String subject, String sender, List<String> recipients) {
        this.message = message;
        this.subject = subject;
        this.recipients = recipients;
        this.sender = sender;
        host = "localhost";
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

    }

    @Override
    public void run() {
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            for (String email:
                    recipients
                 ) {

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }
            message.setSubject(this.subject);
            message.setText(this.message);
            Transport.send(message);
        } catch (MessagingException mex) {
            MessageAlert.showErrorMessage(null, mex.getMessage());
        }
    }

    public void send() {

        start();

    }
}