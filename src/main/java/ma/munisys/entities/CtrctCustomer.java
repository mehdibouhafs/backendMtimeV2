package ma.munisys.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="ctrct_customer")
public class CtrctCustomer {
	
	
	@Id
	private int id;

	@JsonProperty
	@Column(name="ctrt_name")
	private String ctrt_name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dte_strt")
	private Date dteStrt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dte_end")
	private Date dteEnd;
	
	
	
	@ManyToOne
	private Customer customer;
	
	private String pilote;
	
	@Column(name="project_name")
	private String project_name;
	
	@OneToMany(mappedBy="ctrctCustomer",fetch=FetchType.LAZY)
	private Set<CtrctSupplier> contrats;
	
	
	@OneToMany(mappedBy="ctrctCustomer", orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<CtrctCustomerProduct> ctrctCustomerProducts;
	



	@Override
	public String toString() {
		return "CtrctCustomer [id="+ id +"dteStrt=" + dteStrt + ", dteEnd=" + dteEnd + ", ctrtName=" + ctrt_name
				+ ", customer=" + customer + ",code projet = "+ project_name + ", pilote=" + pilote + ", contrats=" + contrats
				+ ", ctrctCustomerProducts=" + ctrctCustomerProducts + "]";
	}
	


	

}
