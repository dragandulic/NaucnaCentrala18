package naucnaCentrala.dto;

import java.util.ArrayList;

public class LaborESDTO {

	
	private String laborname;
	
	private Long magazineid;
	
	private Long scientificareaid;
	
	private String abstractt;
	
	private ArrayList<String> keyterms;
	
	private String pdfname;
	
	
	public String getPdfname() {
		return pdfname;
	}


	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}


	public LaborESDTO() {
		
	}


	public String getLaborname() {
		return laborname;
	}


	public void setLaborname(String laborname) {
		this.laborname = laborname;
	}


	public Long getMagazineid() {
		return magazineid;
	}


	public void setMagazineid(Long magazineid) {
		this.magazineid = magazineid;
	}


	public Long getScientificareaid() {
		return scientificareaid;
	}


	public void setScientificareaid(Long scientificareaid) {
		this.scientificareaid = scientificareaid;
	}


	public String getAbstractt() {
		return abstractt;
	}


	public void setAbstractt(String abstractt) {
		this.abstractt = abstractt;
	}


	public ArrayList<String> getKeyterms() {
		return keyterms;
	}


	public void setKeyterms(ArrayList<String> keyterms) {
		this.keyterms = keyterms;
	}
	
}
