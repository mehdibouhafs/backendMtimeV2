package ma.munisys.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import ma.munisys.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long> {
	
	@Query("select f from Formation f where f.frmName like :x")
	public Page<Formation> chercher(@Param("x") String mc,Pageable pageable);
	
	public Formation findByFrmName(String formationName);
	
	
	
	

}
