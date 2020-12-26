package classes;

import interfaces.ISender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * Класс GmailSender выполняет отправку сообщения по электронной почте Gmail
 */
public class GmailSender implements ISender {
    @Override
    public boolean send(String addressFrom, String password, String addressTo,
                        String subject, String text, File... attachment) {
        if (addressFrom != null && addressTo != null && password != null) {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(addressFrom, password);
                }
            });

            Message emailMessage = new MimeMessage(session);
            try {
                emailMessage.setFrom(new InternetAddress(addressFrom));
                emailMessage.setRecipients(
                        Message.RecipientType.TO, InternetAddress.parse(addressTo));
                if (subject != null) emailMessage.setSubject(subject);

                Multipart multipart = new MimeMultipart();

                if (text != null) {
                    MimeBodyPart mimeText = new MimeBodyPart();
                    mimeText.setText(text);
                    multipart.addBodyPart(mimeText);
                }

                if (attachment != null) {
                    for (File file : attachment) {
                        MimeBodyPart mimeAttachment = new MimeBodyPart();
                        mimeAttachment.attachFile(file);
                        multipart.addBodyPart(mimeAttachment);
                    }
                }

                emailMessage.setContent(multipart);

                Transport.send(emailMessage);
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        return false;
    }
}
