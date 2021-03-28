package ma.munisys.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ma.munisys.entities.Customer;
import ma.munisys.entities.Editeur;

@RepositoryRestResource(collectionResourceRel = "editeurs", path = "editeurs")
public interface EditeurDao extends JpaRepository<Editeur,Long> {
	
	@RestResource(path="all", rel="all")
	@Query("select e from Editeur e")
	public List<Editeur> getAllEditeur();

}
