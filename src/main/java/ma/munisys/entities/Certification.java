package ma.munisys.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	
	@Temporal(TemporalType.DATE)
	private Date dateEch;
	
	@ManyToOne
	private Technologie technologie;
	
	@ManyToOne
	private Editeur editeur;
	
	@OneToMany(mappedBy="certification", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private Set<EmpCertification> candidats = new HashSet<EmpCertification>();
	
	private String idOutlook;
	
	

}
