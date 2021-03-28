package ma.munisys.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class DateFormation {
	
	@Id @GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	private Formation formation;
	
	private String idOutlook;

	public DateFormation() {
		super();
	}


	public DateFormation(Long id, Date date, Formation formation, String idOutlook) {
		super();
		this.id = id;
		this.date = date;
		this.formation = formation;
		this.idOutlook = idOutlook;
	}

	@JsonIgnore
	public Formation getFormation() {
		return formation;
	}


	@JsonProperty
	public void setFormation(Formation formation) {
		this.formation = formation;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getIdOutlook() {
		return idOutlook;
	}


	public void setIdOutlook(String idOutlook) {
		this.idOutlook = idOutlook;
	}
	
}