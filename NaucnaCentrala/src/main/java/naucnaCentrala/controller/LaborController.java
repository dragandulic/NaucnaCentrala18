package naucnaCentrala.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.LaborESDTO;
import naucnaCentrala.model.DBFile;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.UserRepository;
import naucnaCentrala.response.UploadFileResponse;
import naucnaCentrala.service.DBFileService;
import naucnaCentrala.service.EmailService;
import naucnaCentrala.service.LaborService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/labor")
public class LaborController {

	@Autowired
	private LaborService laborService;
	
	@Autowired
	private DBFileService dbFileService;
	
	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/laborsofmagazin/{idm}")
	public ResponseEntity<ArrayList<LaborDTO>> laborsOfMagazin(@PathVariable Long idm) {
		
		ArrayList<LaborDTO> ret = laborService.getLabors(idm);
		
		if(ret != null) {
			return new ResponseEntity<>(ret, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/addlabor")
	public Long addLabor(@RequestBody LaborESDTO labordto) {
		
		Long idl = laborService.addLabor(labordto);
		
		if(idl != null) {
			return idl;
		}
		return null;
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/addpdfinlabor/{idl}")
	public String addPdfInLabor(@RequestParam("file") MultipartFile file, @PathVariable Long idl) throws MailException, InterruptedException {
		
		DBFile dbfile = dbFileService.storeFile(file);
		
		Labor l = laborRepository.findByIdEquals(idl);
		if(l != null) {
			l.setDbfile(dbfile);
			Labor updatelabor = laborRepository.save(l);
			
			if(updatelabor != null) {
				
				String useremail = "";
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					useremail = ((UserDetails)principal).getUsername();
				} else {
					useremail = principal.toString();
				}
				
				User us = userRepository.findByEmail(useremail);
				
				
					emailService.SendUser(us); //slanje maila authoru
				
				
				EditorReviewer e = l.getMagazine().getMaineditor();
				
					emailService.SendEditor(e);
				
				
				return "uspesno";
			}
			else {
				laborRepository.delete(l);
			}
		}
		
		return "nesupesno";	
	}
	
}
