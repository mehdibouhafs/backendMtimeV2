package ma.munisys.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ctrct_supplier")
public class CtrctSupplier {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="dte_strt")
	private Date dteStrt;

	@Temporal(TemporalType.DATE)
	@Column(name="dte_end")
	private Date dteEnd;

	@Column(name="ctrt_name")
	private String ctrtName;

	private String contact;

	private Double price;
	
	private String devise;

	private Double marge;

	private String statut;

	private String commentaire;

	@ManyToOne
	private CtrctCustomer ctrctCustomer;

	@OneToMany(mappedBy = "ctrctSupplier", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<CtrctSupplierProduct> ctrctSupplierProducts;

	public CtrctSupplier() {
		super();
		
	}

	public CtrctSupplier(Date dteStrt, Date dteEnd, String ctrtName, String contact, Double price,
			Double marge, String statut, String commentaire, CtrctCustomer ctrctCustomer,
			Set<CtrctSupplierProduct> ctrctSupplierProducts) {
		super();
		this.dteStrt = dteStrt;
		this.dteEnd = dteEnd;
		this.ctrtName = ctrtName;
		this.contact = contact;
		this.price = price;
		this.marge = marge;
		this.statut = statut;
		this.commentaire = commentaire;
		this.ctrctCustomer = ctrctCustomer;
		this.ctrctSupplierProducts = ctrctSupplierProducts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDteStrt() {
		return dteStrt;
	}

	public void setDteStrt(Date dteStrt) {
		this.dteStrt = dteStrt;
	}

	public Date getDteEnd() {
		return dteEnd;
	}

	public void setDteEnd(Date dteEnd) {
		this.dteEnd = dteEnd;
	}

	public String getCtrtName() {
		return ctrtName;
	}

	public void setCtrtName(String ctrtName) {
		this.ctrtName = ctrtName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMarge() {
		return marge;
	}

	public void setMarge(Double marge) {
		this.marge = marge;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@JsonIgnore
	public CtrctCustomer getCtrctCustomer() {
		return ctrctCustomer;
	}

	@JsonProperty
	public void setCtrctCustomer(CtrctCustomer ctrctCustomer) {
		this.ctrctCustomer = ctrctCustomer;
	}

	public Set<CtrctSupplierProduct> getCtrctSupplierProducts() {
		return ctrctSupplierProducts;
	}

	public void setCtrctSupplierProducts(Set<CtrctSupplierProduct> ctrctSupplierProducts) {
		this.ctrctSupplierProducts = ctrctSupplierProducts;
	}
	
	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	@Override
	public String toString() {
		return "CtrctSupplier [id=" + id + ", dteStrt=" + dteStrt + ", dteEnd=" + dteEnd + ", ctrtName=" + ctrtName
				+ ", contact=" + contact + ", price=" + price + ", marge=" + marge + ", statut=" + statut
				+ ", commentaire=" + commentaire +"]";
	}

	

	

	
	
	

}
