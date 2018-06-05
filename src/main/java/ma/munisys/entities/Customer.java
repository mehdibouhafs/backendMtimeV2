package ma.munisys.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data @AllArgsConstructor 
public class Customer implements Serializable {
	
	@Id 
	private String code;
	private String name;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Project> projects;
	
	public Customer() {
		projects = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Customer [code=" + code + ", name=" + name + "]";
	}
	
	
	
	
}
