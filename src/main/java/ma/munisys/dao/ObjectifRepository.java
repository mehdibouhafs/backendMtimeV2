package ma.munisys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Objectif;

public interface ObjectifRepository extends JpaRepository<Objectif, Long> {
	
	@Query("select o from Objectif o where o.name like :mc order by o.echeance DESC")
	public Page<Objectif> getAllObjectifs(@Param("mc") String mc, Pageable pageable);
	
	@Query("select o from Objectif o join o.users u "
			+ "where u.user.username=:username "
			+ "and o.echeance>=:today")
	public List<Objectif> getMyObjectifsValide(@Param("username") String username, @Param("today") Date today);

}
