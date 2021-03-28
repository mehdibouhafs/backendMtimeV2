package ma.munisys.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Project implements Serializable {
	
	@Id
	private String prjId;
	
	private String prjName;
	
	private String stsPrj;
	
	@ManyToOne
	@JsonIgnore
	private Customer customer;
	
	private Date dteUpdtePrj;
	
	private String leadrPrj;
	
	private String bu;
	
	private double durtionPrj;
	
	private double durtionEnMinutes;
	
	private boolean disabled;
	
}
