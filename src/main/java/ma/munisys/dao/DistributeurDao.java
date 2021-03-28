package ma.munisys.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Distributeur;
import ma.munisys.entities.Product;


public interface DistributeurDao extends JpaRepository<Distributeur, Long> {
	
	@Query("select p from Distributeur p where p.name like :mc")
	public Page<Distributeur> findDistributeurByNameStartsWith(@Param("mc") String mc, Pageable pageable);

}
