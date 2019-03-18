package naucnaCentrala.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.camunda.bpm.engine.task.Task;

import naucnaCentrala.dto.MagazineDTO;
import naucnaCentrala.dto.ResponseDTO;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.service.MagazineService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/magazine")
public class MagazineController {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	
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
	
	
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/startprocescamunda/{idm}")
	public ResponseEntity<ResponseDTO> startprocescamunda(@PathVariable Long idm) {
		
		Map<String, Object> variables = new HashMap<>();
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		Magazine m = magazineRepository.findByIdEquals(idm);
		
		variables.put("maineditor", m.getMaineditor().getEmail());
		variables.put("magazinid", idm);
		variables.put("authoremail", useremail);

		ProcessInstance pi =runtimeService.startProcessInstanceByKey("ModelNC", variables);
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		System.out.println("TASK: " + task.getId());
		
		if(taskService.createTaskQuery().processInstanceId(pi.getId()).list().size()!=0) {
			
			return new ResponseEntity<>(new ResponseDTO("uspesno",task.getId()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseDTO("neuspesno",""),HttpStatus.OK);
		}
		
		//String piId = runtimeService.startProcessInstanceByKey("ModelNC").getId();
		//Task task = taskService.createTaskQuery().processInstanceId(piId).list().get(0);
		
		
	}
	
	
}
