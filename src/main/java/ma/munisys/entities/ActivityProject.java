package ma.munisys.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Activité projet")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeActivite", discriminatorType = DiscriminatorType.STRING)
public class ActivityProject extends Activity implements Serializable {
	
	@ManyToOne
	private Project project;

	public ActivityProject(Long id, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd, double durtion,
			AppUser user, String nature, Customer customer, String ville, String lieu, boolean statut, String comments,
			Project project) {
		super(id, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, nature, customer, ville, lieu, statut, comments);
		
		this.project = project;
	}
	
	
	public ActivityProject(Long id, Date dteStrt, Date dteEnd, String hrStrt, String hrEnd, double durtion,
			AppUser user, String nature, Customer customer, String ville, String lieu, boolean statut,
			String comments) {
		super(id, dteStrt, dteEnd, hrStrt, hrEnd, durtion, user, nature, customer, ville, lieu, statut, comments);
	}
	
	public ActivityProject() {
		super();
	}


	public Project getProject() {
		return project;
	}




	public void setProject(Project project) {
		this.project = project;
	}




	@Override
	public String toString() {
		return "ActivityProject [project=" + project + ", toString()=" + super.toString() + "]";
	}


}
