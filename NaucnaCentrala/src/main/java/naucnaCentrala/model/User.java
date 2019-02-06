package naucnaCentrala.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

@Entity
public class User {

	//user -> to su autor, koautor i korisnik 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private String city;
	
	private String country;
	
	private String email;
	
	private String password;
	
	private String confirmpassword;
	
	@ManyToMany
	private List<Labor> purchasedlabors;
	
	@ManyToMany
	private List<Magazine> purchasedmagazins;
	
	
	@OneToOne
	private MembershipFee membershipfee;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmpassword() {
		return confirmpassword;
	}


	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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


	public MembershipFee getMembershipfee() {
		return membershipfee;
	}


	public void setMembershipfee(MembershipFee membershipfee) {
		this.membershipfee = membershipfee;
	}


	public List<Labor> getPurchasedlabors() {
		return purchasedlabors;
	}


	public void setPurchasedlabors(List<Labor> purchasedlabors) {
		this.purchasedlabors = purchasedlabors;
	}


	public List<Magazine> getPurchasedmagazins() {
		return purchasedmagazins;
	}


	public void setPurchasedmagazins(List<Magazine> purchasedmagazins) {
		this.purchasedmagazins = purchasedmagazins;
	}
	
	
}
