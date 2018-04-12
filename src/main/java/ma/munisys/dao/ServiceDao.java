package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.Service;

@RepositoryRestResource(collectionResourceRel = "service", path = "service")
public interface ServiceDao extends JpaRepository<Service,Long> {

}
