package ma.munisys.entities;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeActivite",discriminatorType = DiscriminatorType.STRING,length=15)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property ="typeActivite")
@JsonSubTypes({
	@Type(name = "Activité projet", value = ActivityProject.class),

})
@Data @AllArgsConstructor @NoArgsConstructor
public class Activity {
	
	@Transient
	private static final String MY_TIME_ZONE="Africa/Casablanca";
	
	@Id @GeneratedValue
	private Long id;
	private Date dteStrt;
    private Date dteEnd;
    private String hrStrt;
    private String hrEnd;
    private String durtion;
    @ManyToOne
    private AppUser user;
    
    private String nature;
    
    @ManyToOne
	private Customer customer;
    
    private String ville;
    
    private String lieu;
    
    private boolean statut;
    

}
