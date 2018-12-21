package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.service.MembershipfeeService;

@RestController
public class MembershipfeeController {

	
	@Autowired
	private MembershipfeeService membershipfeeService;
	
}
