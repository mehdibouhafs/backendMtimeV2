package ma.munisys.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Product {
	
	@Id
	private String ref;
	
	private String desig;
	
	private String editeur;
	
	private String distributeur;

	public Product() {
		super();
	}

	public Product(String ref, String desig, String editeur, String  distributeurs) {
		super();
		this.ref = ref;
		this.desig = desig;
		this.editeur = editeur;
		this.distributeur = distributeurs;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getDistributeur() {
		return distributeur;
	}

	public void setDistributeur(String distributeur) {
		this.distributeur = distributeur;
	}
	
	@Override
	public String toString() {
		return "Product [ref=" + ref + ", desig=" + desig + ", editeur=" + editeur + ", distributeur="
				+ distributeur + "]";
	}

	

	
	
}
