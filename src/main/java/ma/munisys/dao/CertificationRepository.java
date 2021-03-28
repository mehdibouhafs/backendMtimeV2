package ma.munisys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
	
	@Query("select c from Certification c where c.certName like :x order by c.dateEch DESC")
	public Page<Certification> chercher(@Param("x") String mc,Pageable pageable);
	
	@Query("select c from Certification c join c.candidats candidat where candidat.employe.username=:username and c.certName like :x")
	public Page<Certification> getMyCertifications(@Param("username") String username, @Param("x") String mc, Pageable pageable);
	
	@Query("select distinct c from Certification c join c.candidats candidat where candidat.employe.service.id=:idService")
	public Page<Certification> getCertificationToValide(@Param("idService") Long idService, Pageable pageable);
	
	@Query("select c from Certification c join c.candidats candidat where candidat.employe.username=:username and candidat.visa='Accepted' and year(c.dateEch)=year(:today) and month(c.dateEch)=month(:today)")
	public List<Certification> getCertificationOnThisMonth(@Param("username") String username, @Param("today") Date today);

	@Query("select c from Certification c join c.candidats candidat where candidat.employe.username=:username and candidat.visa='Accepted' and year(c.dateEch)=year(:today) and month(c.dateEch)=(month(:today)+1)")
	public List<Certification> getCertificationOnNextMonth(@Param("username") String username, @Param("today") Date today);

}
