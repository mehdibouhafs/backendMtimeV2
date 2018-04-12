package ma.munisys.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.munisys.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	@Query("select a from Activity a order by a.dteStrt ASC")
	public Page<Activity> chercher(Pageable pageable);

}
