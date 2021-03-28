package ma.munisys.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Editeur implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nomEditeur;
	
	
	
	
	public Editeur() {
		//technologies = new HashSet<>();
	}
	
	

	public Editeur(Long id, String nomEditeur) {
		super();
		this.id = id;
		this.nomEditeur = nomEditeur;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getNomEditeur() {
		return nomEditeur;
	}

	public void setNomEditeur(String nomEditeur) {
		this.nomEditeur = nomEditeur;
	}

	
	

}
