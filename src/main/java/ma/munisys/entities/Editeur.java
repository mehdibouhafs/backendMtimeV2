package ma.munisys.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editeur implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nomEditeur;
	
	@OneToMany(mappedBy="editeur")
	private Set<Technologie> technologies;
	
	
	public Editeur() {
		//technologies = new HashSet<>();
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
