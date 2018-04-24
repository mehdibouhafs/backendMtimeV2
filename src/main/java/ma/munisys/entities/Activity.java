package ma.munisys.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeActivite",discriminatorType = DiscriminatorType.STRING,length=25)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property ="typeActivite")
@JsonSubTypes({
	@Type(name = "Activité projet", value = ActivityProject.class),
	@Type(name = "Activité recouvrement", value = ActivityRecouvrement.class),
	@Type(name = "Activité support", value = ActivityRequest.class),
	@Type(name = "Activité congé", value = ActivityHolliday.class),

})
public class Activity implements Serializable {
	
	//@Transient
	//private static final String MY_TIME_ZONE="Africa/Casablanca";
	
	@Id @GeneratedValue
	private Long id;
	@Column(name ="typeActivite" , insertable = false, updatable = false)
	private String typeActivite;
	
	private Date dteStrt;
    private Date dteEnd;
    private String hrStrt;
    private String hrEnd;
    private double durtion;
    @OneToOne(fetch=FetchType.EAGER)
    private AppUser user;
    
    private String nature;
    
    @OneToOne
	private Customer customer;
    
    private String ville;
    
    private String lieu;
    
    private boolean statut;
    
    private String comments;
    
    

	public Activity(Long id, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd, double durtion, AppUser user,
			String nature, Customer customer, String ville, String lieu, boolean statut, String comments) {
		super();
		this.id = id;
		this.dteStrt = dteStrt;
		this.dteEnd = dteEnd;
		this.hrStrt = hrStrt;
		this.hrEnd = hrEnd;
		this.durtion = durtion;
		this.user = user;
		this.nature = nature;
		this.customer = customer;
		this.ville = ville;
		this.lieu = lieu;
		this.statut = statut;
		this.comments = comments;
	}

	
	


	public Activity() {
		
	}
	
	





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public Date getDteStrt() {
		return dteStrt;
	}





	public void setDteStrt(Date dteStrt) {
		this.dteStrt = dteStrt;
	}





	public Date getDteEnd() {
		return dteEnd;
	}





	public void setDteEnd(Date dteEnd) {
		this.dteEnd = dteEnd;
	}





	public String getHrStrt() {
		return hrStrt;
	}





	public void setHrStrt(String hrStrt) {
		this.hrStrt = hrStrt;
	}





	public String getHrEnd() {
		return hrEnd;
	}





	public void setHrEnd(String hrEnd) {
		this.hrEnd = hrEnd;
	}





	public double getDurtion() {
		return durtion;
	}





	public void setDurtion(double durtion) {
		this.durtion = durtion;
	}





	public AppUser getUser() {
		return user;
	}





	public void setUser(AppUser user) {
		this.user = user;
	}





	public String getNature() {
		return nature;
	}





	public void setNature(String nature) {
		this.nature = nature;
	}





	public Customer getCustomer() {
		return customer;
	}





	public void setCustomer(Customer customer) {
		this.customer = customer;
	}





	public String getVille() {
		return ville;
	}





	public void setVille(String ville) {
		this.ville = ville;
	}





	public String getLieu() {
		return lieu;
	}





	public void setLieu(String lieu) {
		this.lieu = lieu;
	}





	public boolean isStatut() {
		return statut;
	}





	public void setStatut(boolean statut) {
		this.statut = statut;
	}





	public String getComments() {
		return comments;
	}





	public void setComments(String comments) {
		this.comments = comments;
	}
	

	public String getTypeActivite() {
		return typeActivite;
	}





	public void setTypeActivite(String typeActivite) {
		this.typeActivite = typeActivite;
	}





	@Override
	public String toString() {
		return "Activity [id=" + id + ", dteStrt=" + dteStrt + ", dteEnd=" + dteEnd + ", hrStrt=" + hrStrt + ", hrEnd="
				+ hrEnd + ", durtion=" + durtion + ", user=" + user + ", nature=" + nature + ", customer=" + customer
				+ ", ville=" + ville + ", lieu=" + lieu + ", statut=" + statut + ", comments=" + comments + "]";
	}
    
    
    

}
