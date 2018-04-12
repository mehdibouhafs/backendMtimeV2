package ma.munisys.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
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
    private Collection<AppUser> users;


}
