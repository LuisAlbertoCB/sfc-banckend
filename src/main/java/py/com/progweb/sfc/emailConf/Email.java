package py.com.progweb.sfc.emailConf;

import py.com.progweb.sfc.entity.Cliente;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Email {
    public void EnviarEmailConfirmacion(Cliente cliente, Integer puntosUtilizado, String concepto, Date fechaUso) throws IOException {
        String destinatario = cliente.getEmail();
        String emailRemitente = "emicoronel000@gmail.com";
        String host = "smtp.gmail.com";
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", emailRemitente);
        props.put("mail.smtp.password", "adminis22021");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailRemitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Email de confimacion de Uso de Puntos");
            message.setText("Estimado Cliente " + cliente.getNombre() + " " + cliente.getApellido() + "\n" +
                    "Utilizo " + puntosUtilizado + " puntos en concepto de " +
                    concepto + " en fecha " + fechaUso.toString());
            Transport transport = session.getTransport("smtp");
            transport.connect(props.getProperty("mail.smtp.host"),
                    props.getProperty("mail.smtp.user"),
                    props.getProperty("mail.smtp.password"));
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Enviado");
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}