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
		
		PaymentObject po = new PaymentObject();
		po.setAmount(1); //cenu treba promeni, ovo je samo za probu
		po.setTitle("Placanja clanarine");
		po.setDescription("Korisnik placa clanarinu u iznosu 1$");
		
		HttpHeaders header = new HttpHeaders();	
		HttpEntity entity = new HttpEntity(po, header);
		
		Long response = restTemplate.postForObject("http://localhost:8051/objectpayment/savepaymentobject", entity, Long.class);
		
		
		System.out.println("REZULTAT: " + response);
		
		
		return response;
	}
	
	
}
