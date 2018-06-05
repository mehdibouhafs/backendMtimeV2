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

	public ActivitySI(Long id, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd, double durtion, AppUser user,
			String nature, Customer customer, String ville, String lieu, boolean statut, String comments,
			String perimetre) {
		super(id, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, nature, customer, ville, lieu, statut, comments);
		this.perimetre = perimetre;
	}

	public String getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(String perimetre) {
		this.perimetre = perimetre;
	}
	
	

}
