package naucnaCentrala.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class EditorReviewer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column 
	private String email;
	
	@Column
	private String city;
	
	@Column
	private String country;
	
	private String password;
	
	private boolean isreviewer;
	
	private boolean iseditor;
	
	@Column
	private String title; //titula valjda 
	
	
	@ManyToMany  //editor moze da ima vise naucnih oblasti a i naucna oblast moze da pripada vise editora
	private Set<ScientificArea> scientificarea = new HashSet<>();   //naucne oblasti koje pokriva

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Role> roles = new HashSet<>();

	
	public EditorReviewer() {
		
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public boolean isIsreviewer() {
		return isreviewer;
	}


	public void setIsreviewer(boolean isreviewer) {
		this.isreviewer = isreviewer;
	}


	public boolean isIseditor() {
		return iseditor;
	}


	public void setIseditor(boolean iseditor) {
		this.iseditor = iseditor;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Set<ScientificArea> getScientificarea() {
		return scientificarea;
	}


	public void setScientificarea(Set<ScientificArea> scientificarea) {
		this.scientificarea = scientificarea;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public List<Role> getRoles() {
		List<Role> retlist = new ArrayList<>(roles);
		if(retlist.size() > 0) {
			return retlist;
		}
		return null;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
	
	
	
}








