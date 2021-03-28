package ma.munisys.dao;

import org.springframework.data.repository.query.Param;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.munisys.entities.Product;


public interface ProductDAO extends JpaRepository<Product, String> {

	
	@Query("select p from Product p where p.ref like :mc or p.desig like :mc or p.editeur like :mc or p.distributeur like :mc")
	public Page<Product> findByNameStartsWith(@Param("mc") String mc, Pageable pageable);
	
	
	
}
