package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.Editeur;

@RepositoryRestResource(collectionResourceRel = "editeur", path = "editeur")
public interface EditeurDao extends JpaRepository<Editeur,Long> {

}
