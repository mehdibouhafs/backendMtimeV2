package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.ObjectifUser;

public interface ObjectifUserRepository extends JpaRepository<ObjectifUser, Long> {
	
	@Modifying
	@Query("update ObjectifUser set taux=:taux where id=:id")
	public void updateTaux(@Param("id") Long id, @Param("taux") double taux);

}
