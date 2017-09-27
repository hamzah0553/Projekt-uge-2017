package Models;

import Models.Ticket;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

    public void sendMail(String mail) {

        final String username = "CinemaWeek17@gmail.com";
        final String password = "biograf123";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Reservationinfo myInfo = new Reservationinfo();
            myInfo.generateTickets(1);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail));
            message.setSubject("Heres is your order");
            message.setText("Hej! \n" + "Her er din billet for filmen " + myInfo.myArray.get(0).getMovieTitle() + ". \n"
                    + "Du har sæde(erne) " + myInfo.myArray.get(0).getSeatId() + ',' + " på rækken " + myInfo.myArray.get(0).getRowNr() + '.'
                    + "\nDatoen for hvornår filmen starter er: " + myInfo.myArray.remove(0).getTicketDate());



            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}