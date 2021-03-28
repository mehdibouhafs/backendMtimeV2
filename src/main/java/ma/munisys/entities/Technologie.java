package ma.munisys.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Technologie implements Serializable {
	
	@Id @GeneratedValue
	private Long idTechnologie;
	
	
	private String nomTechnologie;
	
	@ManyToMany
	private Set<Editeur> editeurs;
	
	public Technologie() {
		
	}
	

	public Technologie(Long idTechnologie, String nomTechnologie, Set<Editeur> editeurs) {
		super();
		this.idTechnologie = idTechnologie;
		this.nomTechnologie = nomTechnologie;
		this.editeurs = editeurs;
	}



	public Long getIdTechnologie() {
		return idTechnologie;
	}

	public void setIdTechnologie(Long idTechnologie) {
		this.idTechnologie = idTechnologie;
	}

	public String getNomTechnologie() {
		return nomTechnologie;
	}

	public void setNomTechnologie(String nomTechnologie) {
		this.nomTechnologie = nomTechnologie;
	}

	public Set<Editeur> getEditeurs() {
		return editeurs;
	}

	public void setEditeurs(Set<Editeur> editeurs) {
		this.editeurs = editeurs;
	}

	
	
	
	

}
