package nagarro.icpprg.testdoubles;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailGateway implements IMessageGateway {
    private Session session;

    public EmailGateway(Session session) {
        this.session = session;
    }

    @Override
    public void send(String contactEmail, String s, String message) {
        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("noreply@example.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contactEmail));
            mimeMessage.setSubject("Mail Subject");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(message, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
