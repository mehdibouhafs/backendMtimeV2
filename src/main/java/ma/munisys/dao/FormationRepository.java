package ma.munisys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long> {
	
	@Query("select f from Formation f where f.frmName like :x")
	public Page<Formation> chercher(@Param("x") String mc,Pageable pageable);
	
	@Query("select f from Formation f join f.candidats candidat where candidat.employe.username=:username and f.frmName like :x")
	public Page<Formation> getMyFormations(@Param("username") String username, @Param("x") String mc, Pageable pageable);
	
	@Query("select distinct f from Formation f join f.candidats candidat where candidat.employe.service.id=:idService")
	public Page<Formation> getFormationToValide(@Param("idService") Long idService, Pageable pageable);
	
	@Query("select distinct f from Formation f join f.candidats candidat where candidat.employe.service.id=:idService")
	public Page<Formation> getFormationGroupe(@Param("idService") Long idService, Pageable pageable);
	

	
}
