package ma.munisys.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import ma.munisys.entities.AppProfile;
import ma.munisys.entities.Customer;
import ma.munisys.entities.Direction;
import ma.munisys.entities.Editeur;
import ma.munisys.entities.Offre;
import ma.munisys.entities.Produit;
import ma.munisys.entities.Project;
import ma.munisys.entities.Service;
import ma.munisys.entities.Technologie;
import ma.munisys.entities.TypeActivity;

/* to expose IDs for specific classes.  */

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
	
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    	// TODO Auto-generated method stub
    	config.exposeIdsFor(Customer.class);
    	config.exposeIdsFor(Project.class);
    	config.exposeIdsFor(TypeActivity.class);
    	config.exposeIdsFor(AppProfile.class);
    	config.exposeIdsFor(AppProfile.class);
    	config.exposeIdsFor(Direction.class);
    	config.exposeIdsFor(Editeur.class);
    	config.exposeIdsFor(Technologie.class);
    	config.exposeIdsFor(Service.class);
    	config.exposeIdsFor(Produit.class);
    	config.exposeIdsFor(Offre.class);
    }

}
