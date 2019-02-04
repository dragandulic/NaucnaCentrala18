package naucnaCentrala.dto;

public class TransactionDTO {

	
	private String magazinename; //kome se placa
	
	private String merchantmail;
	
	private double amount;
	
	private String currency;
	
	private String status;
	
	private String payermail; //ko je platio
	
	private String date;
	
	private String title;
	
	private String type; //bitcoin,paypal, bank
	
	public TransactionDTO() {
		
	}

	public String getMagazinename() {
		return magazinename;
	}

	public void setMagazinename(String magazinename) {
		this.magazinename = magazinename;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayermail() {
		return payermail;
	}

	public void setPayermail(String payermail) {
		this.payermail = payermail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMerchantmail() {
		return merchantmail;
	}

	public void setMerchantmail(String merchantmail) {
		this.merchantmail = merchantmail;
	}
	
	
	
}
