package pl.dmcs.eschool.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EschoolMailerService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String to, String subject, String body) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			InternetAddress toAddress = new InternetAddress(to);

			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);
			message.setContent(body, "text/html; charset=utf-8");

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		javaMailSender.send(message);
	}
}
