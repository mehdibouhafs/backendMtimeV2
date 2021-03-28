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
@DiscriminatorValue("Activité projet")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeActivite", discriminatorType = DiscriminatorType.STRING)
public class ActivityProject extends Activity implements Serializable {

	@ManyToOne
	private Project project;

	private String produit;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ActivityProjet_produits")
	private Set<Produit> produits = new HashSet<>();

	private String precisionProject;

	@ManyToOne
	private Offre offre;

	@ManyToOne
	private Objectif objectif;

	public ActivityProject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityProject(Long id, String typeActivite, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd,
			double durtion, AppUser user, AppUser createdBy, String nature, Customer customer, String ville,
			String lieu, boolean statut, String comments, Date createdAt, Date updatedAt, boolean principal) {
		super(id, typeActivite, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, createdBy, nature, customer, ville, lieu,
				statut, comments, createdAt, updatedAt, principal);
		// TODO Auto-generated constructor stub
	}

	public ActivityProject(Project project, String produit, Set<Produit> produits, String precisionProject, Offre offre,
			Objectif objectif) {
		super();
		this.project = project;
		this.produit = produit;
		this.produits = produits;
		this.precisionProject = precisionProject;
		this.offre = offre;
		this.objectif = objectif;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public String getPrecisionProject() {
		return precisionProject;
	}

	public void setPrecisionProject(String precisionProject) {
		this.precisionProject = precisionProject;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public Objectif getObjectif() {
		return objectif;
	}

	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
	}
	
	

}
