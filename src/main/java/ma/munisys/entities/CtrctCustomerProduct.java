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
public class CtrctCustomerProduct {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Product product;

	@ManyToOne
	private CtrctCustomer ctrctCustomer;
	
	private int qte;
	
	private String devise;
	
	private int qteDisponible; // nb qte dans ctrats 

	@OneToMany(mappedBy="ctrctCustomerProduct", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<CtrctCustomerProductSeries> ctrctCustomerProductSeries;
	
	
	@OneToMany(mappedBy="ctrctCustomerProduct", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<CtrctSupplierProduct> ctrctSupplierProducts;

	public CtrctCustomerProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtrctCustomerProduct(Long id, Product product, CtrctCustomer ctrctCustomer, int qte, String devise,
			int qteDisponible, Set<CtrctCustomerProductSeries> ctrctCustomerProductSeries) {
		super();
		this.id = id;
		this.product = product;
		this.ctrctCustomer = ctrctCustomer;
		this.qte = qte;
		this.devise = devise;
		this.qteDisponible = qteDisponible;
		this.ctrctCustomerProductSeries = ctrctCustomerProductSeries;
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
	public CtrctCustomer getCtrctCustomer() {
		return ctrctCustomer;
	}

	@JsonProperty
	public void setCtrctCustomer(CtrctCustomer ctrctCustomer) {
		this.ctrctCustomer = ctrctCustomer;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	
	public Set<CtrctCustomerProductSeries> getCtrctCustomerProductSeries() {
		return ctrctCustomerProductSeries;
	}

	
	public void setCtrctCustomerProductSeries(Set<CtrctCustomerProductSeries> ctrctCustomerProductSeries) {
		this.ctrctCustomerProductSeries = ctrctCustomerProductSeries;
	}

	@Override
	public String toString() {
		return "CtrctCustomerProduct [id=" + id + ", product=" + product + "Qte = "
				+ qte + "]";
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public int getQteDisponible() {
		return qteDisponible;
	}

	public void setQteDisponible(int qteDisponible) {
		this.qteDisponible = qteDisponible;
	}
	
	
	
	
	@JsonIgnore
	public Set<CtrctSupplierProduct> getCtrctSupplierProducts() {
		return ctrctSupplierProducts;
	}

	public void setCtrctSupplierProducts(Set<CtrctSupplierProduct> ctrctSupplierProducts) {
		this.ctrctSupplierProducts = ctrctSupplierProducts;
	}
	
	

}
