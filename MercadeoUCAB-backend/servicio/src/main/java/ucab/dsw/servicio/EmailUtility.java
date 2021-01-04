package ucab.dsw.servicio;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {

    public static void enviarCorreoElectronico(String destinatario, String asunto, String contenido) throws MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        // Colocar el correo que va a enviar el mensaje
        properties.put("from", "mercadeoucabempresab@gmail.com");
        // Colocar el correo que va a enviar el mensaje
        properties.put("username", "mercadeoucabempresab@gmail.com");
        // Colocar la contraseña de aplicación
        properties.put("password", "dcykjlcozplrcjbh");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(properties.getProperty("from")));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        msg.setSubject(asunto);
        msg.setSentDate(new Date());
        msg.setText(contenido);

        // sends the e-mail
        Transport.send(msg);
    }
}
