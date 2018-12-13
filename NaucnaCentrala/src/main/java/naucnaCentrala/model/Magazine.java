package naucnaCentrala.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Magazine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String issnnumber;
	
	@Column
	private boolean methodpayment; //nacin placanja da li se naplacuje citaocima ili autorima
	//true = open access, clanarinu placaju autori
	//false = clanarinu placa citalac
	
	@ManyToMany
	private Set<ScientificArea> scientificarea = new HashSet<>(); //naucna oblast kojom se bavi
	
	
	@OneToOne
	private EditorReviewer maineditor; //glavni urednik
	
	@OneToMany
	private Set<EditorReviewer> othereditors = new HashSet<>(); //urednici pojedinih naucnih oblasti
	
	
	@ManyToMany
	private Set<EditorReviewer> reviewers = new HashSet<>();
	
	
	
	public Magazine() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIssnnumber() {
		return issnnumber;
	}


	public void setIssnnumber(String issnnumber) {
		this.issnnumber = issnnumber;
	}








	public EditorReviewer getMaineditor() {
		return maineditor;
	}


	public void setMaineditor(EditorReviewer maineditor) {
		this.maineditor = maineditor;
	}


	public Set<EditorReviewer> getOthereditors() {
		return othereditors;
	}


	public void setOthereditors(Set<EditorReviewer> othereditors) {
		this.othereditors = othereditors;
	}


	public Set<EditorReviewer> getReviewers() {
		return reviewers;
	}


	public void setReviewers(Set<EditorReviewer> reviewers) {
		this.reviewers = reviewers;
	}


	public Set<ScientificArea> getScientificarea() {
		return scientificarea;
	}


	public void setScientificarea(Set<ScientificArea> scientificarea) {
		this.scientificarea = scientificarea;
	}


	public boolean isMethodpayment() {
		return methodpayment;
	}


	public void setMethodpayment(boolean methodpayment) {
		this.methodpayment = methodpayment;
	}
	
	
	
	
	
	
	
}
