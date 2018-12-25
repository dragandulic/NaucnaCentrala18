package naucnaCentrala.dto;

public class MagazineDTO {
	
	
	private Long id;
	
	private String name;
	
	private String issnnumber;
	
	private String chifeditor;

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
	
}
