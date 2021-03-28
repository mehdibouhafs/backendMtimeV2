package ma.munisys.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ObjectifUser {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Objectif objectif;
	
	@ManyToOne
	private AppUser user;
	
	private double taux;

	public ObjectifUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjectifUser(Long id, Objectif objectif, AppUser user, double taux) {
		super();
		this.id = id;
		this.objectif = objectif;
		this.user = user;
		this.taux = taux;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Objectif getObjectif() {
		return objectif;
	}

	@JsonProperty
	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

}
