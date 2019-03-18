package naucnaCentrala.service;

import java.util.ArrayList;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naucnaCentrala.model.Magazine;
import naucnaCentrala.model.ScientificArea;
import naucnaCentrala.repository.MagazineRepository;

@Service
public class ScientificAreaService {

	@Autowired
	private MagazineRepository magazineRepository;
	
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	public ArrayList<ScientificArea> getscientificarea(String taskId){
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult(); 
		String processInstanceId = task.getProcessInstanceId(); 
		
		String idm = runtimeService.getVariable(processInstanceId, "magazinid").toString();
		
		Magazine m = magazineRepository.findByIdEquals(Long.valueOf(idm).longValue());
		
		if(m != null) {
			ArrayList<ScientificArea> res = m.getScientificarea();
			
			return res;
		}
		
		return null;
	}
	
}
