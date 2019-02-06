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
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.PaymentObject;
import naucnaCentrala.model.Transaction;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.PaymentObjectRepository;
import naucnaCentrala.repository.TransactionRepository;
import naucnaCentrala.repository.UserRepository;

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
	
	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public String createpaymentobject(Long id,String typee) {
		boolean valido = false;
		if(id != null && typee != null) {
			
			PaymentObject po = new PaymentObject();
			
			if(typee.equals("labor")) {
				Labor l = laborRepository.findByIdEquals(id);
				
				if(l != null) {
					po.setAmount(l.getPricelabor());
					po.setTitle("Kupovina rada '" + l.getTitle() + "'" );
					po.setDescription("Korisnik kupuje jedan rad '" + l.getTitle() + "', cena rada je: " + l.getPricelabor());			
					po.setMerchantid(l.getMagazine().getMerchant_id());
					po.setMerchantpassword(l.getMagazine().getMerchant_password());
					po.setMerchantmail(l.getMagazine().getName());
					
					valido = true;
				}
			}
			else if(typee.equals("magazine")) {
				Magazine m = magazineRepository.findByIdEquals(id);
				
				if(m != null) {
					po.setAmount(m.getAmountMag());
					po.setTitle("Kupovina magazina '" + m.getName() + "'");
					po.setDescription("Korisnik kupuje jedan magazin '" + m.getName() + "', cena magazina je: " + m.getAmountMag());	
					po.setMerchantid(m.getMerchant_id());
					po.setMerchantpassword(m.getMerchant_password());
					po.setMerchantmail(m.getName());
					
					valido = true;
				}

			}
			else if(typee.equals("membershipfee")) {
				Magazine m = magazineRepository.findByIdEquals(id);
				
				if(m != null) {
					po.setAmount(1); //trba namestiti jos cenu clanarine 
					po.setTitle("Placanje clanarine '" + m.getName() + "'");
					po.setDescription("Placa se clanarina, cena clanarine je: " + 1);	
					po.setMerchantid(m.getMerchant_id());
					po.setMerchantpassword(m.getMerchant_password());
					po.setMerchantmail(m.getName());
					
					valido = true;
				}
				
			}
			
			
			if(valido) {
				po.setFronturl("http://localhost:8081/#/dashboard/profile");
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
			
			
		}
		
		return null;
		
	}
	
	
	public String savetransaction(Transaction t) {
		
		if(t!=null) {
			Transaction trans = transactionRepository.save(t);			
			User user = userRepository.findByEmail(t.getPayermail());
			
			if(t.getTitle().contains("rada") && t.getDescription().contains("rada")) {
				
				String s1 = t.getTitle().split("'")[1];
				
				Labor l = laborRepository.findByTitleEquals(s1);
				
				user.getPurchasedlabors().add(l);
				
				userRepository.save(user);
				
			}
			else if(t.getTitle().contains("magazina") && t.getDescription().contains("magazina")) {
				
				
				
				Magazine m = magazineRepository.findByMerchantIdEquals(t.getMerchantid());
				
				user.getPurchasedmagazins().add(m);
				
				userRepository.save(user);
			}

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
				
				String d = t.getDatetime();
				String[] delovi = d.split("T");
				String[] delovi2 = delovi[1].split("\\+");			
				t.setDatetime(delovi[0]+" "+delovi2[0]);
				
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
