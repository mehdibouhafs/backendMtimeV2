package ma.munisys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ma.munisys.entities.Ville;

@RepositoryRestResource(collectionResourceRel = "villes", path = "villes")
public interface VilleDAO extends JpaRepository<Ville, Long> {
	
	@RestResource(path="all", rel="all")
	@Query("select v from Ville v order by v.name ASC")
	public List<Ville> getAllVilles();
	
}
