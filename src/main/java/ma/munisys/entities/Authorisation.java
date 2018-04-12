package ma.munisys.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Authorisation {
	
	@Id @GeneratedValue
	private Long idAuth;
	
	private String authName;

	@Override
	public String toString() {
		return "Authorisation [idAuth=" + idAuth + ", authName=" + authName + "]";
	}
	
	
	
}
