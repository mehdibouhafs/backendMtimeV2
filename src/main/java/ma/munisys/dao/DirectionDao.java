package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.Direction;

@RepositoryRestResource(collectionResourceRel = "direction", path = "direction")
public interface DirectionDao  extends JpaRepository<Direction,Long>{

}
