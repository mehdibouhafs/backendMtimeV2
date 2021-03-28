package ma.munisys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ma.munisys.entities.Produit;

@RepositoryRestResource(collectionResourceRel = "produits", path = "produits")
public interface ProduitDAO extends JpaRepository<Produit, Long> {

	@RestResource(path="all", rel="all")
	@Query("select p from Produit p where p.servName=:servName order by p.produit ASC")
	public List<Produit> getProduits(@Param("servName") String servName);
	
}
