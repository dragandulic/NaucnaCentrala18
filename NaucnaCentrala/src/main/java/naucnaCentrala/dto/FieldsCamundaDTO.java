package naucnaCentrala.dto;

import java.util.ArrayList;

import org.camunda.bpm.engine.form.FormField;


//SALJEM FORM NA FRONT
public class FieldsCamundaDTO {

	private String taskid;
	
	private ArrayList<FormField> fields;
	
	private String procesInstanceId;
	
	
	private FieldsCamundaDTO() {
		
	}
	
	public FieldsCamundaDTO(String taskid, ArrayList<FormField> fields, String procesInstanceId) {
		this.taskid = taskid;
		this.fields = fields;
		this.procesInstanceId = procesInstanceId;
	}

	public String getTaskid() {
		return taskid;
	}


	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}


	public ArrayList<FormField> getFields() {
		return fields;
	}


	public void setFields(ArrayList<FormField> fields) {
		this.fields = fields;
	}


	public String getProcesInstanceId() {
		return procesInstanceId;
	}


	public void setProcesInstanceId(String procesInstanceId) {
		this.procesInstanceId = procesInstanceId;
	}
	
}
