package naucnaCentrala.dto;

public class ReviewerDTO {

	private String name;
	
	private String surname;
	
	private String scientificarea;
	
	private Long id;
	
	public ReviewerDTO() {
		
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getScientificarea() {
		return scientificarea;
	}

	public void setScientificarea(String scientificarea) {
		this.scientificarea = scientificarea;
	}
	
}
