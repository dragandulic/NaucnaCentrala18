package naucnaCentrala.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.MembershipfeeDTO;
import naucnaCentrala.dto.PurchasedPropsDTO;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.UserRepository;
import naucnaCentrala.service.MembershipfeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/membershipfee")
public class MembershipfeeController {
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/mymembershipfee")
	public ResponseEntity<ArrayList<MembershipfeeDTO>> myMembershipFee(){
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		User u = userRepository.findByEmail(useremail);
		
		
		
		ArrayList<MembershipfeeDTO> res = membershipfeeService.mymembershipfee(u.getId());
		
		if(res!=null) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return null;
	}
	
	
	
}
