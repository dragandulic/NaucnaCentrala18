package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.PaymentMembershipFDTOO;
import naucnaCentrala.service.PaymentObjService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/paymentobj")
public class PaymentObjController {

	
	@Autowired
	private PaymentObjService paymentObjService;
	
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/paymentobjmembershipfee/{idm}")
	public Long createPaymentobjectMembershipfee(@PathVariable Long idm) {
		System.out.println("DOSAOOOOOOOOOOOO " + idm);
		Long rez = paymentObjService.createPaymentObjectMembershipfee(idm);
		return rez;
	}
	
	@GetMapping("/getPaymentObj/{id}")
	public ResponseEntity<PaymentMembershipFDTOO> getPaymentObject(@PathVariable Long id){
		
		PaymentMembershipFDTOO obj = paymentObjService.getPayment(id);
		
		if(obj!=null) {
			return new ResponseEntity<>(obj, HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
}
