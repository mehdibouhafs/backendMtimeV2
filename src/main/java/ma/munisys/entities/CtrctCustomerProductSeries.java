package ma.munisys.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
public class CtrctCustomerProductSeries {
	
	@Id @GeneratedValue
	private Long id;
	
	private String sn;

	@ManyToOne
	private CtrctCustomerProduct ctrctCustomerProduct;

	public CtrctCustomerProductSeries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtrctCustomerProductSeries(Long id, String sn, CtrctCustomerProduct ctrctCustomerProduct) {
		super();
		this.id = id;
		this.sn = sn;
		this.ctrctCustomerProduct = ctrctCustomerProduct;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@JsonIgnore
	public CtrctCustomerProduct getCtrctCustomerProduct() {
		return ctrctCustomerProduct;
	}

	@JsonProperty
	public void setCtrctCustomerProduct(CtrctCustomerProduct ctrctCustomerProduct) {
		this.ctrctCustomerProduct = ctrctCustomerProduct;
	}

	@Override
	public String toString() {
		return "CtrctCustomerProductSeries [id=" + id + ", sn=" + sn + "]";
	}
	
	
	

}
