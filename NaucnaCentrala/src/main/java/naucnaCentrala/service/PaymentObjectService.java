package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.PaymentObject;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.PaymentObjectRepository;

@Service
public class PaymentObjectService {

	@Autowired
	private PaymentObjectRepository paymentObjectRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Long createpaymentobject(Long idm) {
		
		Magazine magazine = magazineRepository.findByIdEquals(idm);
		
		if(magazine != null) {
			PaymentObject po = new PaymentObject();
			po.setAmount(1); //cenu treba promeniti, ovo je samo za probu
			po.setTitle("Placanja clanarine");//naslov takodje treba promentiti
			po.setDescription("Korisnik placa clanarinu u iznosu 1$");//opis treba promeniti
			po.setMerchantid(magazine.getMerchant_id());
			po.setMerchantpassword(magazine.getMerchant_password());
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(po, header);
			
			Long response = restTemplate.postForObject("http://localhost:8051/objectpayment/savepaymentobject", entity, Long.class);
			
			
			System.out.println("REZULTAT: " + response);
			
			
			return response;
		}
		
		return null;
	}
	
	
}
