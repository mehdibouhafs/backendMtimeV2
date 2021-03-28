package ma.munisys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class EmpFormation implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String visa;

	@ManyToOne
	private Formation formation;

	@ManyToOne
	private AppUser employe;

	public EmpFormation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public EmpFormation(Long id, String visa, Formation formation, AppUser employe) {
		super();
		this.id = id;
		this.visa = visa;
		this.formation = formation;
		this.employe = employe;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisa() {
		return visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

	@JsonIgnore
	public Formation getFormation() {
		return formation;
	}

	@JsonProperty
	public void setFormation(Formation formation) {
		this.formation = formation;
	}


	public AppUser getEmploye() {
		return employe;
	}

	public void setEmploye(AppUser employe) {
		this.employe = employe;
	}
	
	
	
}
