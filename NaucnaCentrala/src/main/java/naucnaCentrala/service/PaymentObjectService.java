package naucnaCentrala.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import naucnaCentrala.dto.TransactionDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.PaymentObject;
import naucnaCentrala.model.Transaction;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.PaymentObjectRepository;
import naucnaCentrala.repository.TransactionRepository;

@Service
public class PaymentObjectService {

	@Autowired
	private PaymentObjectRepository paymentObjectRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String createpaymentobject(Long idm) {
		System.out.println("METODAAAAAAAAAAAA");
		Magazine magazine = magazineRepository.findByIdEquals(idm);
		
		if(magazine != null) {
			PaymentObject po = new PaymentObject();
			po.setAmount(1); //cenu treba promeniti, ovo je samo za probu
			po.setTitle("Placanja clanarine");//naslov takodje treba promentiti
			po.setDescription("Korisnik placa clanarinu u iznosu 1$");//opis treba promeniti
			po.setMerchantid(magazine.getMerchant_id());
			po.setMerchantpassword(magazine.getMerchant_password());
			po.setMerchantmail(magazine.getName());
			po.setSuccessUrl("http://localhost:8083/paymentobject/savetransaction");
			
			String useremail = "";
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				useremail = ((UserDetails)principal).getUsername();
			} else {
				useremail = principal.toString();
			}
			po.setPayermail(useremail);
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(po, header);
			
			String response = restTemplate.postForObject("http://localhost:8051/objectpayment/savepaymentobject", entity, String.class);
			
			
			return response;
		}
		
		return null;
	}
	
	
	public String savetransaction(Transaction t) {
		
		if(t!=null) {
			Transaction trans = transactionRepository.save(t);
			return "uspeno";
		}
		
		return null;
	}
	
	public ArrayList<TransactionDTO> getmytransaction(){
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		ArrayList<TransactionDTO> retList = new ArrayList<>();
		
		ArrayList<Transaction> listTrans = transactionRepository.findByPayermailEquals(useremail);
		
		for(Transaction t : listTrans) {
			if(t.isVerified()) {
				TransactionDTO trans = new TransactionDTO();
				trans.setAmount(t.getAmount());
				trans.setCurrency(t.getCurrency());
				trans.setDate(t.getDatetime());
				trans.setMerchantmail(t.getMerchantmail());
				trans.setPayermail(t.getPayermail());
				trans.setType(t.getType());
				trans.setTitle(t.getTitle());
				trans.setStatus("paid");
				retList.add(trans);
			}
			
		}
		
		return retList;
	}

	
	
}
