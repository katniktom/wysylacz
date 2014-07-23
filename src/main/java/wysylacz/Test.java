package wysylacz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

public class Test {

	public static void main(String[] args) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("llekutek@gmail.com");
		message.setTo("llekutek@gmail.com");
		message.setSubject("Javacodegeeks email test");
		message.setText("Testing.. \n Hello Spring Email Sender");
		JavaMailSenderImpl ms = new JavaMailSenderImpl();
		ms.setHost("smtp.gmail.com");
		ms.setPort(587);
		ms.setUsername("llekutek");
		ms.setPassword("nananana123");

		Properties p = new Properties();
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.starttls.enable", "true");
		p.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

		ms.setJavaMailProperties(p);
		
		ms.send(message);

	}
}
