package ma.munisys.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.Authorisation;


public interface UserRepository extends JpaRepository<AppUser, String> {
	
	public AppUser findByUsername(String username);
	
	@Query("SELECT u.authorities FROM AppUser u  WHERE u.username=:x")
	public List<Authorisation> findUserAuthority(@Param("x")String username);

}
