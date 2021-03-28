package ma.munisys.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Objectif {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy="objectif", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private Set<ObjectifUser> users = new HashSet<ObjectifUser>();

	private String name;

	@Temporal(TemporalType.DATE)
	private Date echeance;

	public Objectif() {
		super();
	}

	public Objectif(Long id, Set<ObjectifUser> users, String name, Date echeance) {
		super();
		this.id = id;
		this.users = users;
		this.name = name;
		this.echeance = echeance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ObjectifUser> getUsers() {
		return users;
	}

	public void setUsers(Set<ObjectifUser> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEcheance() {
		return echeance;
	}

	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}

}
