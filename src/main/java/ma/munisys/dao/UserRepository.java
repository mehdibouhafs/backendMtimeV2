package ma.munisys.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.Authorisation;


public interface UserRepository extends JpaRepository<AppUser, String> {
	
	public AppUser findByUsername(String username);
	
	@Query("SELECT u.authorities FROM AppUser u  WHERE u.username=:x")
	public List<Authorisation> findUserAuthority(@Param("x")String username);
	
	@Query("select u from AppUser u where u.username like :text or u.lastName like :text or u.firstName like :text")
	public Page<AppUser> searchUser(@Param("text") String text, Pageable pageable);
	
	@Query("select u from AppUser u where u.service.id=:idService and (u.username like :text or u.lastName like :text or u.firstName like :text)")
	public Page<AppUser> searchUserByServiceAnName(@Param("idService") Long idService, @Param("text") String text, Pageable pageable);
	
	@Query("select u from AppUser u where u.service.id in (1,2,3,4,6) order by u.service.id asc")
	public List<AppUser> searchUserByServices();
	

}
