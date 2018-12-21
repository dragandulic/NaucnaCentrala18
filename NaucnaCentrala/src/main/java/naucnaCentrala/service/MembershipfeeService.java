package naucnaCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.repository.MembershipfeeRepository;

@Service
public class MembershipfeeService {

	
	@Autowired
	private MembershipfeeRepository membershipfeeRepository;
	
}
