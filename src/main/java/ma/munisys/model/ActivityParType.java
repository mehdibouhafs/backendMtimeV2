package ma.munisys.model;

import java.io.Serializable;

import javax.persistence.Entity;


public class ActivityParType {
	
	private String type;
	private long nbre;
	
	public ActivityParType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityParType(String type, long nbre) {
		super();
		this.type = type;
		this.nbre = nbre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getNbre() {
		return nbre;
	}

	public void setNbre(long nbre) {
		this.nbre = nbre;
	}
	
	
}
