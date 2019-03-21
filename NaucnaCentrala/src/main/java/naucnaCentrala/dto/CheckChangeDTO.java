package naucnaCentrala.dto;

import java.util.ArrayList;

public class CheckChangeDTO {

	private String authoranswer;
	
	private ArrayList<String> reviewercomment;
	
	private String title;
	
	private String keyterms;
	
	private String apstract;
	
	public CheckChangeDTO() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyterms() {
		return keyterms;
	}

	public void setKeyterms(String keyterms) {
		this.keyterms = keyterms;
	}

	public String getApstract() {
		return apstract;
	}

	public void setApstract(String apstract) {
		this.apstract = apstract;
	}

	public String getAuthoranswer() {
		return authoranswer;
	}

	public void setAuthoranswer(String authoranswer) {
		this.authoranswer = authoranswer;
	}

	public ArrayList<String> getReviewercomment() {
		return reviewercomment;
	}

	public void setReviewercomment(ArrayList<String> reviewercomment) {
		this.reviewercomment = reviewercomment;
	}
	
	
	
	
}
