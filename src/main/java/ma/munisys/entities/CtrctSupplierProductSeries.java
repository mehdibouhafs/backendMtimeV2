package ma.munisys.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity

public class CtrctSupplierProductSeries {
	
	@Id @GeneratedValue
	private Long id;

	@ManyToOne
	private CtrctSupplierProduct ctrctSupplierProduct;
	
	@ManyToOne
	private CtrctCustomerProductSeries ctrctCustomerProductSeries;

	public CtrctSupplierProductSeries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtrctSupplierProductSeries(Long id, CtrctSupplierProduct ctrctSupplierProduct) {
		super();
		this.id = id;
		this.ctrctSupplierProduct = ctrctSupplierProduct;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@JsonIgnore
	public CtrctSupplierProduct getCtrctSupplierProduct() {
		return ctrctSupplierProduct;
	}

	@JsonProperty
	public void setCtrctSupplierProduct(CtrctSupplierProduct ctrctSupplierProduct) {
		this.ctrctSupplierProduct = ctrctSupplierProduct;
	}

	
	
	
}
