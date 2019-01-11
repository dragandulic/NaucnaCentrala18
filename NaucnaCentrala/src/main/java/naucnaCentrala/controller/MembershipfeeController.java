package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.service.MembershipfeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/membershipfee")
public class MembershipfeeController {

	
	@Autowired
	private MembershipfeeService membershipfeeService;
	
	/*
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/payment")
	public String createPaymentobject() {

		String rez = membershipfeeService.createPaymentObject();
		return rez;
	}
	*/
}
