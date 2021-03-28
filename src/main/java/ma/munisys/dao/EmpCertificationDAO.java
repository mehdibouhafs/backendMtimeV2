package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.munisys.entities.EmpCertification;

public interface EmpCertificationDAO extends JpaRepository<EmpCertification, Long> {
	
}
