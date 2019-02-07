package naucnaCentrala.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.PurchasedPropsDTO;
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
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@PostMapping("/loginn")
	public void login(@RequestBody User user) {
		
		System.out.println("DOSAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + user.getEmail());
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/saska")
	public void saska(@RequestBody User user) {
		
		
		
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/purchasedprops")
	public ResponseEntity<ArrayList<PurchasedPropsDTO>> purchasedProps(){
		
		ArrayList<PurchasedPropsDTO> res = userService.purchasedprops();
		
		if(res!=null) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return null;
	}
	
	
	
	
	
}
