package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.service.PaymentObjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/paymentobject")
public class PaymentObjectController {

	
	@Autowired
	private PaymentObjectService paymentObjectService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/createpaymentobject/{idm}")
	public String createPaymentObj(@PathVariable Long idm) {
		
		String res = paymentObjectService.createpaymentobject(idm); 
		
		
		if(res !=null) {
			return res;
		}
		
		return null;
		
	} 
	
	@PostMapping(value="/savetransaction")
	public String saveTransaction() {
	
		System.out.println("DOSAO DA SACUVA TRANSAKCIJUUUUUUUUUUUUUUUUU");
		
		
		return null;
	}
	
	
}
