package naucnaCentrala.dto;

public class MagazineDTO {
	
	
	private Long id;
	
	private String name;
	
	private String issnnumber;
	
	private String chifeditor;
	
	private double amountmag;
	
	private String urldownload;
	
	private String userrole;
	
	private String type; //openaccess or noopenaccess
	
	private String activemembership; //da li korisnik ima aktivnu clanarinu 
	
	private String bought;

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

	public String getChifeditor() {
		return chifeditor;
	}

	public void setChifeditor(String chifeditor) {
		this.chifeditor = chifeditor;
	}

	public double getAmountmag() {
		return amountmag;
	}

	public void setAmountmag(double amountmag) {
		this.amountmag = amountmag;
	}

	public String getUrldownload() {
		return urldownload;
	}

	public void setUrldownload(String urldownload) {
		this.urldownload = urldownload;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActivemembership() {
		return activemembership;
	}

	public void setActivemembership(String activemembership) {
		this.activemembership = activemembership;
	}

	public String getBought() {
		return bought;
	}

	public void setBought(String bought) {
		this.bought = bought;
	}
	
}
