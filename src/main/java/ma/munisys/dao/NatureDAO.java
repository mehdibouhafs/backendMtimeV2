package ma.munisys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ma.munisys.entities.Nature;

@RepositoryRestResource(collectionResourceRel = "nature", path = "nature")
public interface NatureDAO extends JpaRepository<Nature, Long> {


	
	@Query("select n from Nature n where service=:service and n.typeActivity.type=:type order by n.name ASC")
	public List<Nature> findByType(@Param("service") String service, @Param("type") String type);
	
	
	
}
