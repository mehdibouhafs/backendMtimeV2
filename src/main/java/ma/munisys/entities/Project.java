package ma.munisys.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Project {
	
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
	
	private int durtionPrj;
	
	
	
}
