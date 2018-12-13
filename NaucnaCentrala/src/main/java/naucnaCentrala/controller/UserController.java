package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.model.User;
import naucnaCentrala.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/sign-up")
	public void singUp(@RequestBody User user ) {
		
		String rez = userService.singup(user);
		if(rez==null) {
			System.out.println(rez);
		}
		else {
			System.out.println(rez);
		}
	}
	
	
	@PostMapping("/loginn")
	public void login(@RequestBody User user) {
		
		System.out.println("DOSAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		
	}
	
	
}
