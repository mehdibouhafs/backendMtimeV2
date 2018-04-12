package ma.munisys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Technologie implements Serializable {
	
	@Id @GeneratedValue
	private Long idTechnologie;
	
	
	private String nomTechnologie;
	
	@ManyToOne
	private Editeur editeur;
	
	public Technologie() {
		
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

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}
	
	
	
	

}
