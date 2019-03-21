package naucnaCentrala.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
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

import naucnaCentrala.dto.FormFieldsDTO;
import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.LaborESDTO;
import naucnaCentrala.model.DBFile;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.ScientificAreaRepository;
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
	
	@Autowired
	private ScientificAreaRepository scientificAreaRepository;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/laborsofmagazin/{idm}")
	public ResponseEntity<ArrayList<LaborDTO>> laborsOfMagazin(@PathVariable Long idm) {
		
		
		ArrayList<LaborDTO> ret = laborService.getLabors(idm);
		
		if(ret != null) {
			return new ResponseEntity<>(ret, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	//Ovu ne gadjam za dodavanje labora
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/addlabor")
	public Long addLabor(@RequestBody LaborESDTO labordto) {
		System.out.println("Dosao  da doda labor");
		Long idl = laborService.addLabor(labordto);
		System.out.println("DOdaooooooooooo " + idl);
		if(idl != null) {
			return idl;
		}
		return null;
	}
	
	//ova se gadja za doddavanje magazina 
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/addnewlabor/{taskid}")
	public Long addNewLabor(@RequestBody ArrayList<FormFieldsDTO> data, @PathVariable String taskid) {
		
		
		HashMap<String, Object> mapp = new HashMap<>();
		for(FormFieldsDTO f : data) {
			System.out.println(f.getName() + " " + f.getValue());
			mapp.put(f.getName(), f.getValue());
			
		}
		
		Labor newLabor = new Labor();
		newLabor.setTitle(mapp.get("titlelabor").toString());
		newLabor.setAbstrct(mapp.get("abstract").toString());
		newLabor.setKeyterms(mapp.get("keyterms").toString());
		newLabor.setCoautors(mapp.get("coauthors").toString());
		
		ScientificArea sca  = scientificAreaRepository.findByNameEquals(mapp.get("scientificarea").toString());
		newLabor.setScientificarea(sca);
		
		Labor l = laborRepository.save(newLabor);
		return l.getId();
	}
	
	
	
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/addpdfinlabor/{idl}/{taskid}")
	public String addPdfInLabor(@RequestParam("file") MultipartFile file, @PathVariable Long idl, @PathVariable String taskid) throws MailException, InterruptedException {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult(); 
		String processInstanceId = task.getProcessInstanceId(); 
		
		String magazinid = runtimeService.getVariable(processInstanceId, "magazinid").toString();
		
		
		DBFile dbfile = dbFileService.storeFile(file);
		
		Labor l = laborRepository.findByIdEquals(idl);
		if(l != null) {
			l.setDbfile(dbfile);
			Magazine m = magazineRepository.findByIdEquals(Long.valueOf(magazinid).longValue());
			l.setMagazine(m);
			Labor updatelabor = laborRepository.save(l);
			
			if(updatelabor != null) {
				String s = laborService.addLabortask(updatelabor, taskid);
				
				/*
				String useremail = "";
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					useremail = ((UserDetails)principal).getUsername();
				} else {
					useremail = principal.toString();
				}

				User us = userRepository.findByEmail(useremail);
					emailService.SendUser(us,updatelabor); //slanje maila authoru
			
				EditorReviewer e = l.getMagazine().getMaineditor();	
					emailService.SendEditor(e,updatelabor); //slanje maila editoru
				*/
				return "uspesno";
			}
			else {
				laborRepository.delete(l);
			}
		}
		
		return "nesupesno";	
	}

	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping(value="/uploadpdf/{taskid}")
	public String uploadPDF(@RequestParam("file") MultipartFile file, @PathVariable String taskid) throws MailException, InterruptedException {
		
		DBFile dbfile = dbFileService.storeFile(file);

		Task task = taskService.createTaskQuery().taskId(taskid).singleResult(); 
		String processInstanceId = task.getProcessInstanceId(); 
		
		String laborname = runtimeService.getVariable(processInstanceId, "titlelabor").toString();
		
		Labor labor = laborRepository.findByTitleEquals(laborname);
		if(labor==null) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa " + laborname);
		}
		labor.setDbfile(dbfile);
		
		laborRepository.save(labor);
		

		
		HashMap<String, Object> mapp = new HashMap<>();
		mapp.put("pdf", "http://localhost:8038/dbfile/downloadFile=" + labor.getDbfile().getId());
		
		taskService.complete(taskid,mapp); 
		
		return null;
	}
	
}
