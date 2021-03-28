package ma.munisys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import ma.munisys.entities.CtrctCustomer;
import ma.munisys.entities.CtrctSupplier;

@Repository
public interface CtrctSupplierDAO extends JpaRepository<CtrctSupplier, Long> {
	
	@Query("select cf from CtrctSupplier cf where cf.statut = :statut ")
	public List<CtrctSupplier> getAllContratsSupplierByStatutSupplierForCtrClient(@Param("statut") String statut);
	
}
