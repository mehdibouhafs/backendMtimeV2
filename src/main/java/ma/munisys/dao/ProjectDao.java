package ma.munisys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.Project;

@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectDao extends JpaRepository<Project, Long> {
	
	@Query("select p from Project p inner join p.customer c where c.code = :x")
	public List<Project> findProjectByCustomer(@Param("x")String codeCustomer);
}
