package naucnaCentrala.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double amount;
	
	private String title;
	
	private String description;
	
	private String merchantid;
	
	private String merchantpassword;
	
	public PaymentObject() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getMerchantid() {
		return merchantid;
	}



	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}



	public String getMerchantpassword() {
		return merchantpassword;
	}



	public void setMerchantpassword(String merchantpassword) {
		this.merchantpassword = merchantpassword;
	}
	
	
}