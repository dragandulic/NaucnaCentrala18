package naucnaCentrala.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private Set<EditorReviewer> othereditors = new HashSet<>(); //urednici pojedinih naucnih oblasti kao i recezenti
	
	
	@OneToMany
	private Set<EditorSA> editorsa = new HashSet<>(); //urednik naucne oblasti
	
	
	@OneToOne
	private DBFile dbfile;
	
	@ManyToMany
	private Set<EditorReviewer> reviewers = new HashSet<>();
	
	private String merchant_id;
	
	private String merchant_password;
	
	private double amountmag;
	
	private String bitcointoken;
	
	private String clientId;
	
	private String clientSecret;
	
	
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








	public ArrayList<EditorSA> getEditorsa() {
		List<EditorSA> ret = new ArrayList<>(editorsa);
		if(ret.size() > 0) {
			return (ArrayList<EditorSA>) ret;
		}
		return null;
	}


	public void setEditorsa(Set<EditorSA> editorsa) {
		this.editorsa = editorsa;
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


	public ArrayList<ScientificArea> getScientificarea() {
		List<ScientificArea> retlist = new ArrayList<>(scientificarea);
		if(retlist.size() > 0) {
			return (ArrayList<ScientificArea>) retlist;
		}
		return null;
		
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


	public String getMerchant_id() {
		return merchant_id;
	}


	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}


	public String getMerchant_password() {
		return merchant_password;
	}


	public void setMerchant_password(String merchant_password) {
		this.merchant_password = merchant_password;
	}


	public double getAmountMag() {
		return amountmag;
	}


	public void setAmountMag(double amountMag) {
		this.amountmag = amountMag;
	}


	public DBFile getDbfile() {
		return dbfile;
	}


	public void setDbfile(DBFile dbfile) {
		this.dbfile = dbfile;
	}


	public double getAmountmag() {
		return amountmag;
	}


	public void setAmountmag(double amountmag) {
		this.amountmag = amountmag;
	}


	public String getBitcointoken() {
		return bitcointoken;
	}


	public void setBitcointoken(String bitcointoken) {
		this.bitcointoken = bitcointoken;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String getClientSecret() {
		return clientSecret;
	}


	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	
	
	
	
	
	
}
