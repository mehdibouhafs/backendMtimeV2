package ma.munisys.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.munisys.entities.Request;


public interface RequestRepository extends JpaRepository<Request, String> {

	@Query("select r from Request r where r.rqtExcde like :x")
	public Page<Request> chercher(@Param("x") String mc,Pageable pageable);
	
	@Query("select r from Request r join r.users u where u.username=:u and r.rqtExcde like :x")
	public Page<Request> mytickets(@Param("u") String username, @Param("x") String mc,Pageable pageable);
	
}
