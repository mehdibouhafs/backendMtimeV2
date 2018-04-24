package ma.munisys.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.AppUser;
import ma.munisys.entities.Formation;

public interface UserRepository extends JpaRepository<AppUser, String> {
	
	public AppUser findByUsername(String username);
	
	@Query("select f from Formation f join f.participants p where p.username = :x and f.frmName like :y")
	public Page<Formation> findMyFormation(@Param("x") String username, @Param("y") String mc, Pageable pageable);

}
