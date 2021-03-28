package ma.munisys.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Activité congé")
public class ActivityHoliday extends Activity {
	
	private String motif;

	public ActivityHoliday() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityHoliday(Long id, String typeActivite, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd,
			double durtion, AppUser user, AppUser createdBy, String nature, Customer customer, String ville,
			String lieu, boolean statut, String comments, Date createdAt, Date updatedAt, boolean principal) {
		super(id, typeActivite, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, createdBy, nature, customer, ville, lieu, statut,
				comments, createdAt, updatedAt, principal);
		// TODO Auto-generated constructor stub
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	

	
	
	
}
