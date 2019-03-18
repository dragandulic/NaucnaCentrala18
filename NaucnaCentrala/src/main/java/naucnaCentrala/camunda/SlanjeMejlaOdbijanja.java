package naucnaCentrala.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class SlanjeMejlaOdbijanja implements JavaDelegate {

	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		
		String username = (String) execution.getVariable("authoremail");
		
		User u = userRepository.findByEmail(username);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(u.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		
		Magazine m = magazineRepository.findByIdEquals(Long.valueOf(execution.getVariable("magazinid").toString()).longValue());
		
		email.setSubject("Odbijanje rada");
		String text = "Obavestavamo Vas da je rad '" + execution.getVariable("titlelabor").toString() + "' u casopisu '" + m.getName()  + "' odbijen jer nije tematski prikladan.";
				
		email.setText(text);
		System.out.println("Slanje mejla odbojnice...");
		javaMailSender.send(email);
		System.out.println("Mail odbojnice poslat!");
		
	}

}
