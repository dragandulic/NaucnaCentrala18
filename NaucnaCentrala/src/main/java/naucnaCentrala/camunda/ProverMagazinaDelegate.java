package naucnaCentrala.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class ProverMagazinaDelegate implements JavaDelegate {

	@Autowired
	private MagazineRepository magazineRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Prover magazina...");
		
		Long idm = (Long) execution.getVariable("magazinid");
		Magazine m = magazineRepository.findByIdEquals(idm);
		
		if(m.isMethodpayment()) {
			execution.setVariable("isOpenAccess", true);
		}
		else {
			execution.setVariable("isOpenAccess", false);
		}
	}

}
