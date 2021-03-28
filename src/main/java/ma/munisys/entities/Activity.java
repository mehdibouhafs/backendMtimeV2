package ma.munisys.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeActivite", discriminatorType = DiscriminatorType.STRING, length = 25)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeActivite")
@JsonSubTypes({ @Type(name = "Activité projet", value = ActivityProject.class),
		@Type(name = "Activité recouvrement", value = ActivityRecouvrement.class),
		@Type(name = "Activité support", value = ActivityRequest.class),
		@Type(name = "Activité congé", value = ActivityHoliday.class),
		@Type(name = "Activité commerciale", value = ActivityCommercial.class),
		@Type(name = "Activité SI", value = ActivitySI.class),
		@Type(name = "Activité assistance", value = ActivityAssistance.class),
		@Type(name = "Activité PM", value = ActivityPM.class),
		@Type(name = "Activité dev competence", value = ActivityDevCompetence.class),
		@Type(name = "Activité avant vente", value = ActivityAvantVente.class), })
public class Activity implements Serializable {

	 /*@Transient
	 private static final String MY_TIME_ZONE="Africa/Casablanca";*/

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "typeActivite", insertable = false, updatable = false)
	private String typeActivite;

	
	private Date dteStrt;
	

	private Date dteEnd;
	
	private String hrStrt;
	private String hrEnd;
	private double durtion;
	@OneToOne(fetch = FetchType.EAGER)
	private AppUser user;
	
	
	@ManyToOne
	private AppUser createdBy;

	private String nature;

	@OneToOne
	private Customer customer;

	private String ville;

	private String lieu;

	private boolean statut;

	private String comments;

	private Date createdAt;

	private Date updatedAt;

	private boolean principal;
	
	
	
	//private String appointementId;

	public Activity() {
		super();
	}

	public Activity(Long id, String typeActivite, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd,
			double durtion, AppUser user, AppUser createdBy, String nature, Customer customer, String ville,
			String lieu, boolean statut, String comments, Date createdAt, Date updatedAt, boolean principal) {
		super();
		this.id = id;
		this.typeActivite = typeActivite;
		this.dteStrt = dteStrt;
		this.dteEnd = dteEnd;
		this.hrStrt = hrStrt;
		this.hrEnd = hrEnd;
		this.durtion = durtion;
		this.user = user;
		this.createdBy = createdBy;
		this.nature = nature;
		this.customer = customer;
		this.ville = ville;
		this.lieu = lieu;
		this.statut = statut;
		this.comments = comments;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.principal = principal;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
	
	
	@JsonIgnore
	public AppUser getCreatedBy() {
		return createdBy;
	}
	@JsonSetter
	public void setCreatedBy(AppUser createdBy) {
		this.createdBy = createdBy;
	}
	
	

	/*public String getAppointementId() {
		return appointementId;
	}

	public void setAppointementId(String appointementId) {
		this.appointementId = appointementId;
	}*/

	@Override
	public String toString() {
		return "Activity [id=" + id + ", typeActivite=" + typeActivite + ", dteStrt=" + dteStrt + ", dteEnd=" + dteEnd
				+ ", hrStrt=" + hrStrt + ", hrEnd=" + hrEnd + ", durtion=" + durtion + ", user=" + user + ", nature="
				+ nature + ", customer=" + customer + ", ville=" + ville + ", lieu=" + lieu + ", statut=" + statut
				+ ", comments=" + comments + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
