package naucnaCentrala.dto;

public class LaborDTO {

	private String title;
	
	private double pricelabor;
	
	private String urldownload;
	
	private String magazintype; // openaccess or noopenaccess
	
	private String activemembership;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPricelabor() {
		return pricelabor;
	}

	public void setPricelabor(double pricelabor) {
		this.pricelabor = pricelabor;
	}

	public String getUrldownload() {
		return urldownload;
	}

	public void setUrldownload(String urldownload) {
		this.urldownload = urldownload;
	}

	public String getMagazintype() {
		return magazintype;
	}

	public void setMagazintype(String magazintype) {
		this.magazintype = magazintype;
	}

	public String getActivemembership() {
		return activemembership;
	}

	public void setActivemembership(String activemembership) {
		this.activemembership = activemembership;
	}
	
	
	
}
