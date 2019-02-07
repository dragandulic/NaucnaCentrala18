package naucnaCentrala.dto;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import naucnaCentrala.model.Magazine;

public class MembershipfeeDTO {

	
	@Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING,timezone = "Europe/Madrid")
	private Date startdate;
	
	@Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING,timezone = "Europe/Madrid")
	private Date enddate;
	
	
	private String valid;
	
	private String magazine;

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getMagazine() {
		return magazine;
	}

	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}
	
	
	
}
