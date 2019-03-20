package naucnaCentrala.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.EditorSA;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.EditorSARepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class SlanjeMejlaNoviRadUrednikNaucneOblasti implements JavaDelegate {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EditorSARepository editorSARepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		
		String username = (String) execution.getVariable("editorsa");
		System.out.println("AAAAAAAAAA " + username);
		
		EditorSA editorsa = editorSARepository.findByUsernameEquals(username);
		
		if(editorsa != null) {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(editorsa.getEmail());
			email.setFrom(env.getProperty("spring.mail.username"));
			
			Magazine m = magazineRepository.findByIdEquals(Long.valueOf(execution.getVariable("magazinid").toString()).longValue());
			
			email.setSubject("Obavestenje o novom radu");
			String text = "Prijavljen je novi rad '" + execution.getVariable("titlelabor").toString() + "' u casopisu '" + m.getName()  + "' te je potrebno izvrsiti recenziju.";
					
			email.setText(text);
			System.out.println("Slanje mejla, novi rad(recenzija)...");
			javaMailSender.send(email);
			System.out.println("Mail poslat!");
		}
		else {
			
			EditorReviewer editorreview = editorReviewerRepository.findByEmail(username);
			
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(editorreview.getEmail());
			email.setFrom(env.getProperty("spring.mail.username"));
			
			Magazine m = magazineRepository.findByIdEquals(Long.valueOf(execution.getVariable("magazinid").toString()).longValue());
			
			email.setSubject("Obavestenje o novom radu");
			String text = "Obavestava se glavni urednik, jer nema urednika naucne oblasti, da je prijavljen novi rad '" + execution.getVariable("titlelabor").toString() + "' u casopis '" + m.getName()  + "' te je potrebno izvrsiti recenziju.";
					
			email.setText(text);
			System.out.println("Slanje mejla, novi rad(recenzija)...");
			javaMailSender.send(email);
			System.out.println("Mail poslat!");
		}
		
		
		
	}

}
