package naucnaCentrala.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import naucnaCentrala.dto.PaymentBitcoinResponse;
import naucnaCentrala.repository.MembershipfeeRepository;

@Service
public class MembershipfeeService {

	
	@Autowired
	private MembershipfeeRepository membershipfeeRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//placanje
	public String paymentKP() {
		
		
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("title", "Nazivvvv");
		mapa.put("amount", "1");
		
		HttpHeaders header = new HttpHeaders();
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String,Object>>(mapa,header);
		
		String response = restTemplate.postForObject("http://localhost:8051/kpcontroller/payment", entity, String.class);  
		
		System.out.println("TTTTTTTTTTTTTTTTTTTTT " + response);
		
		return response;
		
	}
	
	
	public String createPaymentObject() {
		
		
		
		
		return "";
	}
	
}
