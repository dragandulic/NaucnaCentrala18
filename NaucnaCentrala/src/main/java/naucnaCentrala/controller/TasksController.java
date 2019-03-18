package naucnaCentrala.controller;

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

import naucnaCentrala.dto.FieldsCamundaDTO;
import naucnaCentrala.dto.ResponseDTO;
import naucnaCentrala.dto.ReviewLaborDTO;
import naucnaCentrala.dto.TaskCamunda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Path;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/task")
public class TasksController {

	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/mytasks")
	public ResponseEntity<ArrayList<TaskCamunda>> myTasks() {
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(useremail).list();
		ArrayList<TaskCamunda> retlist = new ArrayList<>();
		for(Task t : tasks) {
			retlist.add(new TaskCamunda(t.getName(), t.getId()));
		}
		
		return new ResponseEntity<>(retlist,HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/gototask/{taskid}")
	public ResponseEntity<ResponseDTO> goToTaks(@PathVariable String taskid) {
		
		List<Task> list = taskService.createTaskQuery().list();
		ResponseDTO ret = new ResponseDTO();
		for(Task t : list) {
			if(t.getId().equals(taskid)) {
				
				ret.setDotaskpath(naStranicu(t.getName()));
				ret.setStatus("uspesno");
				ret.setTaksid(t.getId());
				 
			
				break;
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	private String naStranicu(String name) {
		switch(name){
			case "Autor unosi podatke o radu": 
				return "insertDataForLabor";
			case "Pregled prijavljenog rada":
				return "reviewlabor";
			case "Pregled pdf":
				return "reviewpdf";
		}
		return "ostani";
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/getform/{taskid}")
	public ResponseEntity<FieldsCamundaDTO> getForm(@PathVariable String taskid) {

		List<String> lista = new ArrayList<>();
		lista.add("Saska");
		lista.add("Dragan");
		lista.add("Marko");
		System.out.println(lista.get(2));
		
		TaskFormData taskFormData = formService.getTaskFormData(taskid);
		ArrayList<FormField> formfields = (ArrayList<FormField>) taskFormData.getFormFields();
		
		return new ResponseEntity<>(new FieldsCamundaDTO(taskid, formfields, ""), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/reviewlabor/{taskid}")
	public ResponseEntity<ReviewLaborDTO> Reviewlabor(@PathVariable String taskid) {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String proceInstanceId = task.getProcessInstanceId();
		
		String title = runtimeService.getVariable(proceInstanceId, "titlelabor").toString();
		String apstract = runtimeService.getVariable(proceInstanceId, "abstract").toString();
		String keyterms = runtimeService.getVariable(proceInstanceId, "keyterms").toString();
		
		ReviewLaborDTO ret = new ReviewLaborDTO();
		ret.setTitle(title);
		ret.setApstract(apstract);
		ret.setKeyterms(keyterms);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/completereviewlabor/{taskid}/{res}")
	public ResponseEntity<String> completeReviewLabor(@PathVariable String taskid, @PathVariable String res) {
		
		HashMap<String, Object> mapp = new HashMap<>();
		
		if(res.equals("relevantan")) {
			mapp.put("radRelevantan", true);
		}
		if(res.equals("nerelevantan")) {
			mapp.put("radRelevantan", false);
		}
		
		taskService.complete(taskid,mapp);
		return new ResponseEntity<>("uspeno", HttpStatus.OK);
		
		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/reviewpdf/{taskid}")
	public ResponseEntity<String> reviePDF(@PathVariable String taskid) {
		
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		String urlDownloadPdf = (String) runtimeService.getVariable(procesInstanceId, "pdf");
		
		
		return new ResponseEntity<>(urlDownloadPdf, HttpStatus.OK);
		
	}
	
	
	
	
}
