package ma.munisys.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Activité SI")
public class ActivitySI extends Activity {
	
	private String perimetre;

	


	public ActivitySI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivitySI(Long id, String typeActivite, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd,
			double durtion, AppUser user, AppUser createdBy, String nature, Customer customer, String ville,
			String lieu, boolean statut, String comments, Date createdAt, Date updatedAt, boolean principal) {
		super(id, typeActivite, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, createdBy, nature, customer, ville, lieu, statut,
				comments, createdAt, updatedAt, principal);
		// TODO Auto-generated constructor stub
	}
	
	

	public ActivitySI(String perimetre) {
		super();
		this.perimetre = perimetre;
	}

	public String getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(String perimetre) {
		this.perimetre = perimetre;
	}
	
	

}
