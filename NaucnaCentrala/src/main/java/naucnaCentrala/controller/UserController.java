package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.model.User;
import naucnaCentrala.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {

	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/sign-up")
	public String singUp(@RequestBody User user ) {
		
		String rez = userService.singup(user);
		return rez;
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/loginn")
	public void login(@RequestBody User user) {
		
		System.out.println("DOSAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + user.getEmail());
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/saska")
	public void saska(@RequestBody User user) {
		
		
		
	}
	
	
}
