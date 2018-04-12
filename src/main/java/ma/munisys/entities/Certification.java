package ma.munisys.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Certification {
	
	@Id @GeneratedValue
	private Long id;
	
	private String certName;
	
	private String url;
	
	@Temporal(TemporalType.DATE)
	private Date dateEch;
	
	private int statut;
	
	

}
