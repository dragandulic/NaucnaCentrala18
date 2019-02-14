package naucnaCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/editoreviewer")
public class EditorReviewerController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/whoislogged")
	public String whoIsLogged() {
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		String logged = "";
		User u = userRepository.findByEmail(useremail);
		if(u!=null) {
			logged = u.getRoles().get(0).getName();
		}
		else {
			EditorReviewer e = editorReviewerRepository.findByEmail(useremail);
			if(e!=null) {
				logged = e.getRoles().get(0).getName();
			}
		}
		
		
		
		return logged;
		
	}
	
	
	
	
	
	
	
	
}
