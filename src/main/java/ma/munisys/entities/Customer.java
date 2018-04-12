package ma.munisys.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor 
public class Customer implements Serializable {
	
	@Id 
	private String code;
	private String name;
	
	@OneToMany(mappedBy="customer")
	@JsonIgnore
	private Set<Project> projects;
	
	public Customer() {
		projects = new HashSet<>();
	}
	
}
