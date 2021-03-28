package ma.munisys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ma.munisys.entities.Offre;

@RepositoryRestResource(collectionResourceRel = "offres", path = "offres")
public interface OffreDao extends JpaRepository<Offre, String> {
	
	@RestResource(path="bycustomer", rel="bycustomer")
	@Query("select o from Offre o where o.customer.code=:code")
	public List<Offre> findOffreByCustomer(@Param("code") String code);
	
}
