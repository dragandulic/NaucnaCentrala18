package naucnaCentrala.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;


import naucnaCentrala.dto.MembershipfeeDTO;
import naucnaCentrala.dto.PaymentBitcoinResponse;
import naucnaCentrala.model.MembershipFee;
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
		
		
		return response;
		
	}
	
	
	public String createPaymentObject() {
		
		
		
		
		return "";
	}
	
	
	public ArrayList<MembershipfeeDTO> mymembershipfee(Long iduser){
		
		ArrayList<MembershipFee> retlist = membershipfeeRepository.findByUser_idEquals(iduser);
		
		ArrayList<MembershipfeeDTO> listm = new ArrayList<>();
		
		for(MembershipFee m : retlist) {
			MembershipfeeDTO mesfdto = new MembershipfeeDTO();
			mesfdto.setStartdate(m.getStartdate());
			mesfdto.setEnddate(m.getEnddate());
			mesfdto.setMagazine(m.getMagazin().getName());
			
			
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			Date now=null;
			try {
				now = formatter.parse(timeStamp);
				} catch (ParseException e) {
				  e.printStackTrace();
				}
			
			if(now.compareTo(m.getEnddate())<=0  && now.compareTo(m.getStartdate())>=0){
				mesfdto.setValid("validm");
			}
			else {
				mesfdto.setValid("novalidm");
			}
			
			listm.add(mesfdto);
		}
		
		return listm;
	}
	
	
	
	
}
