package naucnaCentrala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	
	@OneToOne
	private DBFile dbfile;
	
	private String finalversion;
	
	@ManyToOne
	private Magazine magazine;

	private double pricelabor;
	
	private String state;
	//verified - rad je provaren i prihvacen
	//processing - rad se proverava
	//rejected - rad je odbijen i proces se terminira
	
	public Labor() {
		
	}
	
	
	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
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

	public String getFinalversion() {
		return finalversion;
	}

	public void setFinalversion(String finalversion) {
		this.finalversion = finalversion;
	}


	public Magazine getMagazine() {
		return magazine;
	}


	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}


	public double getPricelabor() {
		return pricelabor;
	}


	public void setPricelabor(double pricelabor) {
		this.pricelabor = pricelabor;
	}


	public DBFile getDbfile() {
		return dbfile;
	}


	public void setDbfile(DBFile dbfile) {
		this.dbfile = dbfile;
	}
	
}
