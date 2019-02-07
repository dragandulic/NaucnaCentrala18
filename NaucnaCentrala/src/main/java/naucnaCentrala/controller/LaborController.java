package naucnaCentrala.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.model.Labor;
import naucnaCentrala.service.LaborService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/labor")
public class LaborController {

	@Autowired
	private LaborService laborService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping("/laborsofmagazin/{idm}")
	public ResponseEntity<ArrayList<LaborDTO>> laborsOfMagazin(@PathVariable Long idm) {
		
		ArrayList<LaborDTO> ret = laborService.getLabors(idm);
		
		if(ret != null) {
			return new ResponseEntity<>(ret, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
