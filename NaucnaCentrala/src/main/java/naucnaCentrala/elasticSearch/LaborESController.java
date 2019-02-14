package naucnaCentrala.elasticSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.LaborESDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/elasticsearch")
public class LaborESController {

	@Autowired
	private LaborESService laborESService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@PostMapping(value="/addlabores")
	public ResponseEntity<String> addLaborES(@RequestBody LaborESDTO l){
		
		String res = laborESService.saveLaborES(l);
		
		if(res != null) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/searchlaborname/{name}")
	public ResponseEntity<List<LaborES>> searchLaborName(@PathVariable String name){
		
		List<LaborES> res = laborESService.searchLaborName(name);
		
		if(res.size() != 0) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/searchmagazinename/{name}")
	public ResponseEntity<List<LaborES>> searchMagazinename(@PathVariable String name){
		
		List<LaborES> res = laborESService.searchMagazineName(name);
		
		if(res.size() != 0) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/searchscientificarea/{name}")
	public ResponseEntity<List<LaborES>> searchScientificArea(@PathVariable String name){
		
		List<LaborES> res = laborESService.searchScientificArea(name);
		
		if(res.size() != 0) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/searchauthor/{name}")
	public ResponseEntity<List<LaborES>> searchAuthor(@PathVariable String name){
		
		List<LaborES> res = laborESService.searchAuthor(name);
		
		if(res != null) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@GetMapping(value="/searchkeyterm/{name}")
	public ResponseEntity<List<LaborES>> searchkeyterm(@PathVariable String name){
		
		List<LaborES> res = laborESService.searchkeyterm(name);
		
		if(res != null) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR')")
	@PutMapping(value="/searcandor")
	public ResponseEntity<List<LaborES>> searcandor(@RequestBody SearchDTO searchdto){
		
		List<LaborES> res = laborESService.searchAndOr(searchdto);
		
		if(res != null) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
