package naucnaCentrala.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.service.ScientificAreaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/scientificarea")
public class ScientificAreaController {

	@Autowired
	private ScientificAreaService scientificAreaService;
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/getscientificareao/{idm}")
	public ResponseEntity<ArrayList<ScientificArea>> getScientificArea(@PathVariable Long idm){
		
		ArrayList<ScientificArea> res = scientificAreaService.getscientificarea(idm);
		
		if(res != null) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
