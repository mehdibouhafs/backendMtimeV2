package ma.munisys.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Certification;
import ma.munisys.entities.Formation;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
	
	@Query("select c from Certification c where c.certName like :x")
	public Page<Certification> chercher(@Param("x") String mc,Pageable pageable);
	
	public Certification findByCertName(String certifName);
	
	
	
	

}
