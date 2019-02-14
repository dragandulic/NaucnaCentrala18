package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.User;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.core.env.Environment;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	
	
	public void SendUser(User user) throws MailException, InterruptedException {
		
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		
		email.setSubject("Poziv na predstavu/film");
		String text = "Text maila";
				
		email.setText(text);
		
		javaMailSender.send(email);
		
	}
	
	
	public void SendEditor(EditorReviewer er) throws MailException, InterruptedException {
		
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(er.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		
		email.setSubject("Poziv na predstavu/film");
		String text = "Text maila";
				
		email.setText(text);
		
		javaMailSender.send(email);
		
	}
	
	
}
