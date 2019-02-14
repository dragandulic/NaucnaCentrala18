package naucnaCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.service.MagazineService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/magazine")
public class MagazineController {

	
	@Autowired
	private MagazineService magazineService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/listofmagazines")
	public ResponseEntity<List<MagazineDTO>> listOfMagazines() {
		
		List<MagazineDTO> ret = magazineService.listofMagazine();
		
		if(ret != null) {
			return new ResponseEntity<>(ret, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/checkmembershipfee/{id}")
	public String checkMembershipFee(@PathVariable Long id) {
		
		String ret = magazineService.chechmembershipfee(id);
		return ret;
	}
	
	
	
	
	
	
}
