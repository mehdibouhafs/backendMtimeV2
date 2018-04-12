package ma.munisys.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Formation {
	
	@Id @GeneratedValue
	private Long id;
	
	private String frmName;
	

	@Temporal(TemporalType.DATE)
	private Date dateStrt;
	

	@Temporal(TemporalType.DATE)
	private Date dateEnd;
	
	private Boolean hasCertif;

	@Override
	public String toString() {
		return "Formation [id=" + id + ", frmName=" + frmName + ", dateStrt=" + dateStrt + ", dateEnd=" + dateEnd
				+ ", hasCertif=" + hasCertif + "]";
	}
	
	

}
