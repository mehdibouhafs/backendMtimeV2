package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.TypeActivity;

@RepositoryRestResource(collectionResourceRel = "typeActivity", path = "typeActivity")
public interface TypeActivityRepository extends JpaRepository<TypeActivity, Long>{

}
