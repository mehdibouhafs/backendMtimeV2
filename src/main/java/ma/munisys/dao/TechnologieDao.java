package ma.munisys.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ma.munisys.entities.Technologie;

@RepositoryRestResource(collectionResourceRel = "technologies", path = "technologies")
public interface TechnologieDao extends JpaRepository<Technologie, Long> {
	
	@RestResource(path="startwith", rel="startwith")
	@Query("select t from Technologie t where t.nomTechnologie like '%'+:name+'%'")
	public Page<Technologie> findByNameStartWith(@Param("name") String name, Pageable pageable); 
	
	
}
