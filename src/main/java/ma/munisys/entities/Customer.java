package ma.munisys.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data @AllArgsConstructor
@Table(name="customer")
public class Customer implements Serializable {
	
	@Id 
	private String code;
	private String name;
	private boolean disabled;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Project> projects;
	
	
	@OneToMany(mappedBy="customer",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<CtrctCustomer> ctrctCustomer;
	
	public Customer() {
		projects = new HashSet<>();
		ctrctCustomer = new HashSet<>();
	}
	
	

	@Override
	public String toString() {
		return "Customer [code=" + code + ", name=" + name + "]";
	}



	public Customer(String code) {
		super();
		this.code = code;
	}
	
	
	
	
}
