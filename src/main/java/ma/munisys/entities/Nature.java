package ma.munisys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Nature implements Serializable {

	@Id @GeneratedValue
	Long id;
	String name;
	String service;
	@ManyToOne
	TypeActivity typeActivity;
	public Nature() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nature(Long id, String name, TypeActivity typeActivity) {
		super();
		this.id = id;
		this.name = name;
		this.typeActivity = typeActivity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TypeActivity getTypeActivity() {
		return typeActivity;
	}
	public void setTypeActivity(TypeActivity typeActivity) {
		this.typeActivity = typeActivity;
	}
	
	
	
}
