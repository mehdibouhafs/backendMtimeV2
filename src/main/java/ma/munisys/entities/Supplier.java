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
@Table(name="supplier")
public class Supplier implements Serializable {
	
	@Id 
	private String Code;
	private String name;
	private boolean disabled;

	

	
	public Supplier() {
	}
	
	

	@Override
	public String toString() {
		return "Customer [code=" + Code + ", name=" + name + "]";
	}



	public Supplier(String code) {
		super();
		this.Code = code;
	}
	
	
	
	
}
