package naucnaCentrala.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class SlanjeMejlaRadPrihvacen implements JavaDelegate {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private LaborRepository laborRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		
		String username = (String) execution.getVariable("authoremail");
		
		User u = userRepository.findByEmail(username);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(u.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		
		Magazine m = magazineRepository.findByIdEquals(Long.valueOf(execution.getVariable("magazinid").toString()).longValue());
		
		email.setSubject("Rad prihvacen");
		String text = "Obavestavamo Vas da je rad '" + execution.getVariable("titlelabor").toString() + "' u casopisu '" + m.getName()  + "' PRIHVACEN.";
				
		email.setText(text);
		System.out.println("Slanje mejla da je rad prihvacen...");
		javaMailSender.send(email);
		System.out.println("Mail prihvatanja poslat!");
		
		
		String titlelabor = (String) execution.getVariable("titlelabor");
		
		Labor l = laborRepository.findByTitleEquals(titlelabor);
		l.setState("verified");
		laborRepository.save(l);
		u.getPurchasedlabors().add(l);
		userRepository.save(u);
		
	}

}
