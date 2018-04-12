package ma.munisys.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.Technologie;

@RepositoryRestResource(collectionResourceRel = "technologies", path = "technologies")
public interface TechnologieDao extends JpaRepository<Technologie, Long> {

	
}
