package pl.isa.biblioteka.model;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailSender {

    public static void main(String[] args) {
        sendToAssignee();
    }

    public static void sendToAssignee() {
        // Dane konfiguracyjne serwera SMTP
        String host = "smtp.wp.pl";
        int port = 465;
        String username = "projektkonstruktorsi@wp.pl";
        String password = "1234!@#$qwerQWER";

        // Adres nadawcy i odbiorcy
        String fromEmail = "projektkonstruktorsi@wp.pl";
        String toEmail = "projektkonstruktorsi@wp.pl";

        // Tworzenie właściwości
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.**ssl.enable", "true");
        props.put("mail.smtp.**ssl.required", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Tworzenie autentykatora
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // Tworzenie sesji
        Session session = Session.getInstance(props, auth);

        try {
            // Tworzenie wiadomości e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Przykładowa wiadomość e-mail");
            message.setText("To jest treść wiadomości.");

            // Wysyłanie wiadomości
            Transport.send(message);

            System.out.println("Wiadomość e-mail została wysłana.");

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



}