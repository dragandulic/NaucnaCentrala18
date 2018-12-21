package naucnaCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.service.MagazineService;

@CrossOrigin(origins = "http://localhost:8081/#/")
@RestController
@RequestMapping(value = "/magazine")
public class MagazineController {

	
	@Autowired
	private MagazineService magazineService;
	
	
	@GetMapping("/listofmagazines")
	public ResponseEntity<List<Magazine>> listOfMagazines() {
		
		List<Magazine> ret = magazineService.listofMagazine();
		
		if(ret != null) {
			return new ResponseEntity<>(ret, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	
	
	
	
	
	
}
