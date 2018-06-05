package ma.munisys.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorValue("Activité assistance")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeActivite", discriminatorType = DiscriminatorType.STRING)
public class ActivityAssistance extends Activity {
	
	

}
