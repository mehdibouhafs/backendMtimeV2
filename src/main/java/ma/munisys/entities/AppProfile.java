package ma.munisys.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppProfile {
	@Id @GeneratedValue
	private Long id;
	
	private String prflName;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Authorisation> authorities = new HashSet<>();

	@Override
	public String toString() {
		return "AppProfile [id=" + id + ", prflName=" + prflName + ", authoritiesProfile=" + authorities + "]";
	}

	
}
