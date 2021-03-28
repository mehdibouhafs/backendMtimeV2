package ma.munisys.dao;

import java.util.List;
import ma.munisys.service.UserService;
import ma.munisys.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.CtrctCustomer;


public interface CtrctCustomerDAO extends JpaRepository<CtrctCustomer, Long> {
	
	@Query("select c from CtrctCustomer c where c.ctrt_name  LIKE '%'+:mc+'%' ")
	public List<String> getAllNameContrat(@Param("mc") String mc);

	@Query("select c.firstName+' '+upper(c.lastName) from AppUser c where c.username  LIKE '%'+:pl+'%' ")
	public List<String> getPiloteName(@Param("pl") String pl);
	
	@Query("select c from CtrctCustomer c where c.customer.code = :cl and c.ctrt_name is not null and pilote LIKE '%'+:user ")
	public List<CtrctCustomer> getContratClientByClient(@Param("cl") String cl,@Param("user") String user);
	
	@Query("select c from CtrctCustomer c where c.ctrt_name = :ctrt_name and c.customer.code = :client")
	public List<CtrctCustomer> getContratClientByContrat(@Param("ctrt_name") String ctrt_name,@Param("client") String client);
	
	@Query("select c from CtrctCustomer c where c.customer.code = :cli and c.ctrt_name is NOT NULL ")
	public List<CtrctCustomer> getAllContratClientByContrat(@Param("cli") String cli);
	
	@Query("select c from CtrctCustomer c where c.project_name = 'test fake'")
	public List<CtrctCustomer> getContratClientByContratProject();
	
	@Query("select c from CtrctCustomer c where c.ctrt_name is not null and c.project_name is not null")
	public List<CtrctCustomer> getAllContratsClients();
	
	@Query("select c from CtrctCustomer c where c.ctrt_name is not null and c.project_name is not null and pilote LIKE '%'+:pilote")
	public List<CtrctCustomer> getAllContratsClientsByPilote(@Param("pilote") String pilote);
	
	@Query("select c.id from CtrctCustomer c where c.ctrt_name = :ctrt_name and c.project_name = :code_project")
	public String getIdByContratName(@Param("ctrt_name") String ctrt_name,@Param("code_project")String code_project);
	
	
	
	@Query("select c.id from CtrctCustomer c where c.ctrt_name = :ctrt_name and c.customer.code = :client")
	public String getIdByContratNameClient(@Param("ctrt_name") String ctrt_name,@Param("client")String client);
	

	@Query("select count(*) from CtrctCustomer c")
	public int getNumberOfContracts();
	
	
	
	
}
