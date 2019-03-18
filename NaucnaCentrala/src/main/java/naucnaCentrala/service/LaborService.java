package naucnaCentrala.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import naucnaCentrala.dto.LaborDTO;
import naucnaCentrala.dto.LaborESDTO;
import naucnaCentrala.model.EditorReviewer;
import naucnaCentrala.model.Labor;
import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.MembershipFee;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.model.User;
import naucnaCentrala.repository.EditorReviewerRepository;
import naucnaCentrala.repository.LaborRepository;
import naucnaCentrala.repository.MagazineRepository;
import naucnaCentrala.repository.MembershipfeeRepository;
import naucnaCentrala.repository.ScientificAreaRepository;
import naucnaCentrala.repository.UserRepository;

@Service
public class LaborService {

	@Autowired
	private LaborRepository laborRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private MembershipfeeRepository membershipfeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EditorReviewerRepository editorReviewerRepository;
	
	@Autowired
	private ScientificAreaRepository scientificAreaRepository;
	
	@Autowired
	private TaskService taskService;
	
	public ArrayList<LaborDTO> getLabors(Long idm){
		
		String useremail = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails)principal).getUsername();
		} else {
			useremail = principal.toString();
		}
		User u = userRepository.findByEmail(useremail);
		EditorReviewer r= editorReviewerRepository.findByEmail(useremail);
		Magazine m = magazineRepository.findByIdEquals(idm);
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		boolean pom = false;
		
		if(m!=null && u!=null) {
			MembershipFee membershipfee = membershipfeeRepository.findByMagazine_idEqualsAndUser_idEquals(m.getId(), u.getId());

			if(membershipfee != null) {
				Date now=null;
				try {
					now = formatter.parse(timeStamp);
					} catch (ParseException e) {
					  e.printStackTrace();
					}
				
				if(now.compareTo(membershipfee.getEnddate())<=0  && now.compareTo(membershipfee.getStartdate())>=0){
					pom = true;
				}
				else {
					pom = false;
				}
			}
			else {
				pom = false;
			}
		}
		
		
		
		if(m != null) {
			ArrayList<Labor> res = laborRepository.findByMagazine_idEquals(idm);
			
			ArrayList<LaborDTO> retlist = new ArrayList<>();
			
			for(Labor l : res) {
				LaborDTO labordto = new LaborDTO();
				if(m.isMethodpayment()) {
					labordto.setMagazintype("openaccess");
				}
				else {
					labordto.setMagazintype("noopenaccess");
				}
				labordto.setId(l.getId());
				labordto.setTitle(l.getTitle());
				labordto.setPricelabor(l.getPricelabor());
				labordto.setUrldownload("http://localhost:8038/dbfile/downloadFile=" + l.getDbfile().getId());
				if( r!=null) {
					labordto.setRole("EDITOR");
				}
				
				if(pom) {
					labordto.setActivemembership("validmembershipf");
				}
				else {
					labordto.setActivemembership("novalidmembershipf");
				}
				
				if(u != null) {
					labordto.setBought("nobought");
					for(int k=0;k<u.getPurchasedlabors().size();k++) {
						if(u.getPurchasedlabors().get(k).getTitle().equals(l.getTitle())) {
							labordto.setBought("yesbought");
						}
					}
				}
				
				
				retlist.add(labordto);
			}
			
			
			return retlist;
		}
		return null;
	}
	
	
	
	public Long addLabor(LaborESDTO labordto) {
		
		
		Labor l = new Labor();
		
		if(labordto != null) {
			l.setTitle(labordto.getLaborname());
			l.setAbstrct(labordto.getAbstractt());
			if(labordto.getScientificareaid() != null) {
				ScientificArea s = scientificAreaRepository.findByIdEquals(labordto.getScientificareaid());
				l.setScientificarea(s);
			}
			if(labordto.getMagazineid() != null) {
				Magazine m = magazineRepository.findByIdEquals(labordto.getMagazineid());
				l.setMagazine(m);
			}
			String terms = "";
			for(int i=0;i<labordto.getKeyterms().size();i++) {
				if(i == labordto.getKeyterms().size() - 1) {
					terms = terms + labordto.getKeyterms().get(i);
				}
				else {
					terms = terms + labordto.getKeyterms().get(i) + ",";
				}
				 
			}
			l.setKeyterms(terms);
			System.out.println("Terms: " + l.getKeyterms());
			l.setState("processing");
			Labor newlabor = laborRepository.save(l);
			return newlabor.getId();
		}
		
		
		return null;
	}
	
	
	

	public String addLabortask(Labor labor, String taskid) {
		
		//Task task1 = taskService.createTaskQuery().processInstanceId(procesid).singleResult();
		
		
		Map<String, Object> variables = new HashMap<>();
		
		variables.put("titlelabor",labor.getTitle());
		variables.put("keyterms", labor.getKeyterms());
		variables.put("scientificarea",labor.getScientificarea().getName());
		variables.put("abstract", labor.getAbstrct());
		variables.put("pdf", "http://localhost:8038/dbfile/downloadFile=" + labor.getDbfile().getId());
		variables.put("coauthors", labor.getCoautors());
		
		
		taskService.complete(taskid, variables);
		
		/*
		Task task2 = taskService.createTaskQuery().processInstanceId(procesid).singleResult();
		System.out.println("(3)Aktivni task ID=" + task2.getId());
		*/
		
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
