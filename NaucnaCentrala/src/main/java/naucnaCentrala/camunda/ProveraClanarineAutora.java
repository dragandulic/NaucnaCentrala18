package naucnaCentrala.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ProveraClanarineAutora implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Provera clanarine autora...");
		
		execution.setVariable("clanarinaAktivna", true);
		
		
		
	}

}
