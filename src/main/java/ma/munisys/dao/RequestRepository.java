package ma.munisys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.munisys.entities.Request;


public interface RequestRepository extends JpaRepository<Request, String> {

	@Query("select r from Request r where r.rqtExcde like :x")
	public Page<Request> chercher(@Param("x") String mc,Pageable pageable);
	
	@Query("select r from Request r join r.users u where u.username=:u and (r.rqtExcde like :x or r.cpyInCde.name like :x) and r.rqtStsInCde>0 order by r.rqtDte DESC")
	public Page<Request> mytickets(@Param("u") String username, @Param("x") String mc,Pageable pageable);
	
	@Query("select r from Request r join r.users u where u.service.id=:id and r.rqtExcde like :x and r.rqtStsInCde>0 order by r.rqtDte DESC")
	public Page<Request> ticketsGroupe(@Param("id") Long id, @Param("x") String mc,Pageable pageable);
	
	
	@Query("select r from Request r where r.cpyInCde.code =:x and (r.rqtNatDsc is null or r.rqtNatDsc!='Maintenance préventive') order by r.rqtDte DESC")
	public List<Request> getTicketByCustomer( @Param("x") String codeClient);
	
	@Query("select r from Request r where (r.cpyInCde.code =:x  and r.rqtDte >= :date) and (r.rqtNatDsc is null or r.rqtNatDsc!='Maintenance préventive') order by r.rqtDte DESC")
	public List<Request> getTicketByCustomer( @Param("x") String codeClient , @Param("date") Date date);
	
	
	@Query("select r from Request r where r.cpyInCde.code =:x and r.rqtNatDsc=:z order by r.rqtDte DESC")
	public List<Request> getTicketByCustomerAndNature( @Param("x") String codeClient ,@Param("z")String nature);
	
	
	
}
