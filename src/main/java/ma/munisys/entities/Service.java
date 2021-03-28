package ma.munisys.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "services")
public class Service implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	
	private String servName;
	
	@ManyToOne
	private Direction direction;
	
	@ManyToOne
	private AppUser responsable;
	
	public Service() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServName() {
		return servName;
	}

	public void setServName(String servName) {
		this.servName = servName;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@JsonIgnore
	public AppUser getResponsable() {
		return responsable;
	}

	public void setResponsable(AppUser responsable) {
		this.responsable = responsable;
	}
	
	
	
	
	
	

}
