package ma.munisys.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  @NoArgsConstructor
public class Request {
	
    @Id
    private String rqtExcde;
    
    private String objDsc;
    
    private String objIdentVal;
    
    private String cntExcde;
    
    private Date rqtDte;
    
    @ManyToOne
    private Customer cpyInCde;
    
    private String rqtNatDsc;
    
    private int rqtStsInCde;
    
    @ManyToMany(fetch=FetchType.LAZY)
    private Set<AppUser> users = new HashSet<>();
    
    

	public Request(String rqtExcde, String objDsc, String objIdentVal, String cntExcde, Date rqtDte, Customer cpyInCde,
			String rqtNatDsc, int rqtStsInCde, Set<AppUser> users) {
		super();
		this.rqtExcde = rqtExcde;
		this.objDsc = objDsc;
		this.objIdentVal = objIdentVal;
		this.cntExcde = cntExcde;
		this.rqtDte = rqtDte;
		this.cpyInCde = cpyInCde;
		this.rqtNatDsc = rqtNatDsc;
		this.rqtStsInCde = rqtStsInCde;
		this.users = users;
	}
	
	
    
    
    
    


}
