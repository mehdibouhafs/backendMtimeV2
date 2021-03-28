package ma.munisys.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Activité dev competence")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeActivite", discriminatorType = DiscriminatorType.STRING)
public class ActivityDevCompetence extends Activity implements Serializable {
	
	private String produit;
	private boolean etat;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ActivityDevCompetence_produits")
	private Set<Produit> produits = new HashSet<>();
	


	public ActivityDevCompetence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityDevCompetence(Long id, String typeActivite, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd,
			double durtion, AppUser user, AppUser createdBy, String nature, Customer customer, String ville,
			String lieu, boolean statut, String comments, Date createdAt, Date updatedAt, boolean principal) {
		super(id, typeActivite, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, createdBy, nature, customer, ville, lieu, statut,
				comments, createdAt, updatedAt, principal);
		// TODO Auto-generated constructor stub
	}
	
	

	public ActivityDevCompetence(String produit, boolean etat) {
		super();
		this.produit = produit;
		this.etat = etat;
		
	}


	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}



	public String getProduit() {
		return produit;
	}



	public void setProduit(String produit) {
		this.produit = produit;
	}

	public Set<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
	
	
	
	
	
	

	

}
