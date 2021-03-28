package ma.munisys.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
public class CtrctSupplierProduct {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne
	private Product product;

	@ManyToOne
	private CtrctSupplier ctrctSupplier;
	
	

	private Double prixUnitaire;

	private int qte;
	
	
	@ManyToOne
	private CtrctCustomerProduct ctrctCustomerProduct;
	
	


	@OneToMany(mappedBy = "ctrctSupplierProduct", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<CtrctSupplierProductSeries> ctrctSupplierProductSeries;
	
	

	public CtrctSupplierProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtrctSupplierProduct(Long id, Product product, CtrctSupplier ctrctSupplier, double prixUnitaire, int qte,
			Set<CtrctSupplierProductSeries> ctrctSupplierProductSeries) {
		super();
		this.id = id;
		this.product = product;
		this.ctrctSupplier = ctrctSupplier;
		this.prixUnitaire = prixUnitaire;
		this.qte = qte;
		this.ctrctSupplierProductSeries = ctrctSupplierProductSeries;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@JsonIgnore
	public CtrctSupplier getCtrctSupplier() {
		return ctrctSupplier;
	}

	@JsonProperty
	public void setCtrctSupplier(CtrctSupplier ctrctSupplier) {
		this.ctrctSupplier = ctrctSupplier;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	
	public Set<CtrctSupplierProductSeries> getCtrctSupplierProductSeries() {
		return ctrctSupplierProductSeries;
	}

	public void setCtrctSupplierProductSeries(Set<CtrctSupplierProductSeries> ctrctSupplierProductSeries) {
		this.ctrctSupplierProductSeries = ctrctSupplierProductSeries;
	}

	@Override
	public String toString() {
		return "CtrctSupplierProduct [id=" + id + ", product=" + product + ", prixUnitaire=" + prixUnitaire + ", qte="
				+ qte + "]";
	}

	

	
	public CtrctCustomerProduct getCtrctCustomerProduct() {
		return ctrctCustomerProduct;
	}

	public void setCtrctCustomerProduct(CtrctCustomerProduct ctrctCustomerProduct) {
		this.ctrctCustomerProduct = ctrctCustomerProduct;
	}
	
	

}
