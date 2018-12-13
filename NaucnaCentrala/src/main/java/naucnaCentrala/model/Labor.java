package naucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Labor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@ManyToOne
	private ScientificArea scientificarea; //naucna oblast u koju se rad svrstava
	
	private String abstrct;
	
	private String keyterms;
	
	private String pdf;
	
	private String finalversion;

	
	
	public Labor() {
		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ScientificArea getScientificarea() {
		return scientificarea;
	}

	public void setScientificarea(ScientificArea scientificarea) {
		this.scientificarea = scientificarea;
	}

	public String getAbstrct() {
		return abstrct;
	}

	public void setAbstrct(String abstrct) {
		this.abstrct = abstrct;
	}

	public String getKeyterms() {
		return keyterms;
	}

	public void setKeyterms(String keyterms) {
		this.keyterms = keyterms;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getFinalversion() {
		return finalversion;
	}

	public void setFinalversion(String finalversion) {
		this.finalversion = finalversion;
	}
	
}
