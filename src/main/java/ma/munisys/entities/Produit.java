package ma.munisys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produit implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	private String produit;
	private String groupe;
	private String servName;

	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produit(Long id, String produit, String groupe, String servName) {
		super();
		this.id = id;
		this.produit = produit;
		this.groupe = groupe;
		this.servName = servName;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getServName() {
		return servName;
	}

	public void setServName(String servName) {
		this.servName = servName;
	}

}
