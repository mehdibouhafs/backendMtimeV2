package ma.munisys.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Activité projet")
@Data @AllArgsConstructor @NoArgsConstructor
public class ActivityProject extends Activity {
	
	@ManyToOne
	private Project project;
	
}
