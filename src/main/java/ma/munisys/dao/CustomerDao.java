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

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerDao extends JpaRepository<Customer, Long> {
	
	@RestResource(path="startwith", rel="startwith")
	@Query("select c from Customer c where c.name like '%'+:name+'%'")
	public Page<Customer> findByNameStartsWith(@Param("name") String name, Pageable pageable);
	
	@RestResource(path="supplierstartwith", rel="supplierstartwith")
	@Query("select c from Customer c where c.name like '%'+:name+'%'")
	public Page<Customer> findSuppliers(@Param("name") String name, Pageable pageable);
	

}
