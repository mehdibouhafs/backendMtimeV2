package ma.munisys.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Activité commerciale")
public class ActivityCommercial extends Activity {

	public ActivityCommercial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityCommercial(Long id, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd, double durtion,
			AppUser user, String nature, Customer customer, String ville, String lieu, boolean statut,
			String comments) {
		super(id, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, nature, customer, ville, lieu, statut, comments);
		// TODO Auto-generated constructor stub
	}

}
