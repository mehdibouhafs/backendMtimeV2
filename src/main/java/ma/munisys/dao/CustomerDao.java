package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.Customer;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerDao extends JpaRepository<Customer, Long> {
	
	

}
