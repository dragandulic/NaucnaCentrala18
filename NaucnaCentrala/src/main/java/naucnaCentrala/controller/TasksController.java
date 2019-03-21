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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import naucnaCentrala.dto.CheckChangeDTO;
import naucnaCentrala.dto.FieldsCamundaDTO;
import naucnaCentrala.dto.FormFieldsDTO;
import naucnaCentrala.dto.MarkDTO;
import naucnaCentrala.dto.OverviewLaborDTO;
import naucnaCentrala.dto.ResponseDTO;
import naucnaCentrala.dto.ReviewLaborDTO;
import naucnaCentrala.dto.ReviewerDTO;
import naucnaCentrala.dto.TaskCamunda;
import naucnaCentrala.model.EditorSA;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.Reviewer;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.ReviewerRepository;

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

//U magazinu tehnika naucna oblast MASINSTVO nema urednika naucne oblasti
//U magazinu bankarstvo naucna oblast EKONOMIJA nema recenzente
//U magazinu gradjevinarstvo naucna oblast SAOBRACAJ nema recenzente
//Za naucnu oblsat ELEKTROTEHNIKA revjueris su : 1,2,3,4
//Za naucnu oblsat MASINSTVO revjueris su : 5,6,7
//Za naucnu oblsat FINANSIJE revjueris su : 1,5,8,9
//Za naucnu oblsat ELEKTROTEHNIKA revjueris su : 1,2,3,4
//Za naucnu oblsat ZELEZNICA revjueris su : 10,11


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
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private ReviewerRepository reviewerRepository;
	
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
			case "Autor ponovo unosi pdf":
				return "labornotformatted";
			case "Izbor recezenata":
				return "choosereviewer";
			case "Pregled rada unos komentara autoru i uredniku i procene":
				return "reviewertask";
			case "Pregled ocena":
				return "overviewdecisions";
			case "Ispravljanje gresaka rada i upload novog pdf-a":
				return "fixanduploadlabor";
			case "Provera zahtevanih izmena":
				return "checkchanges";
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
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping("/completereviewpdf/{taskid}/{result}")
	public ResponseEntity<String> completeReviewPdf(@PathVariable String taskid, @PathVariable String result, @RequestBody FormFieldsDTO dataa) {
	
		HashMap<String, Object> mapp = new HashMap<>();
		if(result.equals("accept")) {
			mapp.put("formatRada", true);
			mapp.put("komentarurednika", "");
			
			Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
			String procesInstanceId = task.getProcessInstanceId();
			
			Long idm = (Long) runtimeService.getVariable(procesInstanceId, "magazinid");
			String scientificarea = (String) runtimeService.getVariable(procesInstanceId, "scientificarea");
			Magazine magazine = magazineRepository.findByIdEquals(idm);
			
			boolean pom = false;
			for(EditorSA e : magazine.getEditorsa()) {
				if(e.getScientificaArea().getName().equals(scientificarea)) {
					mapp.put("editorsa", e.getUsername());
					pom = true;
				}
			} 
			if(!pom) {
				String maindeditor = (String) runtimeService.getVariable(procesInstanceId, "maineditor");
				mapp.put("editorsa", maindeditor);
				pom = true;
			}
			
		}
		else if(result.equals("decline")) {
			mapp.put("formatRada", false);
			mapp.put("komentarurednika", dataa.getValue());
		}
		
		taskService.complete(taskid, mapp);
		return new ResponseEntity<>("uspesno", HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/labornotformatted/{taskid}")
	public ResponseEntity<ArrayList<FormFieldsDTO>> laborNotFormated(@PathVariable String taskid) {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		String titleLabor = (String) runtimeService.getVariable(procesInstanceId, "titlelabor");
		String coauthors = (String) runtimeService.getVariable(procesInstanceId, "coauthors");
		String keyterms = (String) runtimeService.getVariable(procesInstanceId, "keyterms");
		String scientificarea = (String) runtimeService.getVariable(procesInstanceId, "scientificarea");
		String apstract = (String) runtimeService.getVariable(procesInstanceId, "abstract");
		String pdf = (String) runtimeService.getVariable(procesInstanceId, "pdf");
		String komentarurednika = (String) runtimeService.getVariable(procesInstanceId, "komentarurednika");
		
		FormFieldsDTO field1 = new FormFieldsDTO("titleLabor", titleLabor);
		FormFieldsDTO field2 = new FormFieldsDTO("coauthors", coauthors);
		FormFieldsDTO field3 = new FormFieldsDTO("keyterms", keyterms);
		FormFieldsDTO field4 = new FormFieldsDTO("scientificarea", scientificarea);
		FormFieldsDTO field5 = new FormFieldsDTO("apstract", apstract);
		FormFieldsDTO field6 = new FormFieldsDTO("pdf", pdf);
		FormFieldsDTO field7 = new FormFieldsDTO("komentarurednika", komentarurednika);

		ArrayList<FormFieldsDTO> ret = new ArrayList<>();
		ret.add(field1);
		ret.add(field2);
		ret.add(field3);
		ret.add(field4);
		ret.add(field5);
		ret.add(field6);
		ret.add(field7);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/reviewrofmagazine/{taskid}")
	public ResponseEntity<ArrayList<ReviewerDTO>> reviewerOfMagazine(@PathVariable String taskid){
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		
		Long idm = (Long) runtimeService.getVariable(procesInstanceId, "magazinid");
		String scientificarea = (String) runtimeService.getVariable(procesInstanceId, "scientificarea");
		Magazine magazine = magazineRepository.findByIdEquals(idm);
		
		ArrayList<Reviewer> listReviewer = (ArrayList<Reviewer>) reviewerRepository.findAll();
		ArrayList<ReviewerDTO> retList = new ArrayList();
		for(ScientificArea s : magazine.getScientificarea()) {
			for(Reviewer r : listReviewer) {
				for(ScientificArea sa : r.getScientificaArea()) {
					if(sa.getName().equals(s.getName())) {
						ReviewerDTO reviewerdto = new ReviewerDTO();
						reviewerdto.setName(r.getName());
						reviewerdto.setSurname(r.getSurname());
						reviewerdto.setScientificarea(s.getName());
						reviewerdto.setId(r.getId());
						retList.add(reviewerdto);
					}
				}	
			}
		}
		System.out.println("SIZEEEEEEEEEE: " + retList.size());
		
		
		
		return new ResponseEntity<>(retList, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/filtereviwer/{taskid}")
	public ResponseEntity<ArrayList<ReviewerDTO>> filterReviewer(@PathVariable String taskid){
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		
		Long idm = (Long) runtimeService.getVariable(procesInstanceId, "magazinid");
		String scientificarea = (String) runtimeService.getVariable(procesInstanceId, "scientificarea");
		Magazine magazine = magazineRepository.findByIdEquals(idm);
		
		ArrayList<Reviewer> listReviewer = (ArrayList<Reviewer>) reviewerRepository.findAll();
		ArrayList<ReviewerDTO> retList = new ArrayList();
		System.out.println("AAAAAAAAAAa");
		
			for(Reviewer r : listReviewer) {
				for(ScientificArea sa : r.getScientificaArea()) {
					if(sa.getName().equals(scientificarea)) {
						ReviewerDTO rev = new ReviewerDTO();
						rev.setName(r.getName());
						rev.setSurname(r.getSurname());
						rev.setScientificarea(sa.getName());
						rev.setId(r.getId());
						retList.add(rev);
					}
				}	
			}
		
		
		
		System.out.println("SIZEEEEEEEEEE: " + retList.size());
		
		
		
		return new ResponseEntity<>(retList, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping("/addreviewers/{taskid}")
	public void addReviewers(@PathVariable String taskid,@RequestBody ArrayList<String> reviewerid) {
		
		ArrayList<Reviewer> listReviewer = new ArrayList<>();
		for(String s : reviewerid) {
			Reviewer r = reviewerRepository.findByIdEquals(Long.valueOf(s).longValue());
			listReviewer.add(r);
			
		}
		
		List<String> odabraniRecezenti = new ArrayList();
		for(Reviewer r : listReviewer) {
			odabraniRecezenti.add(r.getUsername());
		}
		
		HashMap<String, Object> mapp = new HashMap<>();
		
		mapp.put("listaRecenzenata", odabraniRecezenti);
		taskService.complete(taskid, mapp);;
		
	
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@PostMapping("/overviewlabor/{taskid}")
	public void overviewLabor(@PathVariable String taskid, @RequestBody OverviewLaborDTO o) {
		
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		
		
		
		HashMap<String, Object> mapp = new HashMap<>();
		mapp.put("recezent_komentaruredniku_" + useremail, o.getCommentEditor());
		mapp.put("recezent_komentarautoru_" + useremail, o.getCommentAuthor());
		mapp.put("recezent_preporuka_" + useremail, o.getRating());
		
		taskService.complete(taskid, mapp);
		
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/getdecisionsofreviewer/{taskid}")
	public ResponseEntity<ArrayList<MarkDTO>> getDecisionsOfReviewer(@PathVariable String taskid) {
		

		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		List<String> reviewerusername = (List<String>) runtimeService.getVariable(procesInstanceId, "listaRecenzenata");
		
		ArrayList<MarkDTO> rev = new ArrayList<>();
		
		for(String s : reviewerusername) {
			
			String commentForEditor= (String) runtimeService.getVariable(procesInstanceId, "recezent_komentaruredniku_" + s);
			String reting= (String) runtimeService.getVariable(procesInstanceId, "recezent_preporuka_" + s);
			MarkDTO m = new MarkDTO();
			m.setComment(commentForEditor);
			m.setMark(reting);
			rev.add(m);
		}
		
		
		
		return new ResponseEntity<>(rev, HttpStatus.OK);
		
	}

	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/completeoverviewdecision/{procena}/{taskid}")
	public void completeOverviewLabor(@PathVariable String taskid, @PathVariable String procena) {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		HashMap<String, Object> mapp = new HashMap<>();
		mapp.put("ocena", procena);
		
		taskService.complete(taskid, mapp);
		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/getcommentforauthor/{taskid}")
	public ResponseEntity<ArrayList<MarkDTO>> getCommentForAuthor(@PathVariable String taskid) {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		List<String> reviewerusername = (List<String>) runtimeService.getVariable(procesInstanceId, "listaRecenzenata");
		
		ArrayList<MarkDTO> rev = new ArrayList<>();
		
		for(String s : reviewerusername) {
			
			String commentForEditor= (String) runtimeService.getVariable(procesInstanceId, "recezent_komentarautoru_" + s);
			
			MarkDTO m = new MarkDTO();
			m.setComment(commentForEditor);
			m.setMark("");
			
			rev.add(m);
		}
		
		return new ResponseEntity<>(rev, HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/authoranswer/{taskid}/{answer}")
	public void authoranswer(@PathVariable String taskid, @PathVariable String answer) {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		
		runtimeService.setVariable(procesInstanceId, "authoranswer", answer);
		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/checkchanges/{taskid}")
	public ResponseEntity<CheckChangeDTO> checkChanges(@PathVariable String taskid) {
		
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		String procesInstanceId = task.getProcessInstanceId();
		
		List<String> reviewerusername = (List<String>) runtimeService.getVariable(procesInstanceId, "listaRecenzenata");
		CheckChangeDTO m = new CheckChangeDTO();
		ArrayList<String> li = new ArrayList<>();
		for(String s : reviewerusername) {
			
			String commentForAuthor= (String) runtimeService.getVariable(procesInstanceId, "recezent_komentarautoru_" + s);
			
			li.add(commentForAuthor);
			
		}
		m.setReviewercomment(li);
		String authoranswer = (String) runtimeService.getVariable(procesInstanceId, "authoranswer");
		m.setAuthoranswer(authoranswer);
		String title = (String) runtimeService.getVariable(procesInstanceId, "titlelabor");
		String keyterms = (String) runtimeService.getVariable(procesInstanceId, "keyterms");
		String apstract = (String) runtimeService.getVariable(procesInstanceId, "abstract");
		m.setApstract(apstract);
		m.setKeyterms(keyterms);
		m.setTitle(title);
		return new ResponseEntity<>(m, HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('EDITOR')")
	@GetMapping("/completecheckchanges/{taskid}/{procena}")
	public void completeCheckChanges(@PathVariable String taskid,@PathVariable String procena) {
		
		HashMap<String, Object> mapp = new HashMap<>();
		
		if(procena.equals("PRIHVATI")) {
			mapp.put("zadovoljan", true);
		}
		else if(procena.equals("POPRAVI LABOR")) {
			mapp.put("zadovoljan", false);
		}
		
		taskService.complete(taskid,mapp);
		
	}
	
}
