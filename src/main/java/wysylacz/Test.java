package wysylacz;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Test {
	public static void main(String[] args) throws MessagingException {
		{
			String host = "poczta.interia.pl";
			String Password = "distefanoi";
			String from = "katniktom@interia.pl";
			
			String toAddress = "llekutek@gmail.com";
			String filename = "C:/Users/Tomasz/Desktop/appendix.txt";
			// ("C:/Users/Tomasz/Desktop/CV-Tomasz K¹tnik - 2014_02_PL_PM.pdf");
			// Get system properties
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getInstance(props, null);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, toAddress);
			message.setSubject("Mail z zalcznikiem by TK");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("To jest mail z zalacznikiem");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			try {
				Transport tr = session.getTransport("smtps");
				tr.connect(host, from, Password);
				tr.sendMessage(message, message.getAllRecipients());
				System.out.println("Mail Sent Successfully");
				tr.close();
			} catch (SendFailedException sfe) {
				System.out.println(sfe);
			}
		}
	}
}
