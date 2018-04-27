package ma.munisys.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.munisys.entities.Activity;


public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	@Query("select a from Activity a where a.user.username = :x order by a.dteStrt ASC")
	public Page<Activity> getUserActivities(@Param("x")String username,Pageable pageable);
	
	@Query("select a from Activity a where a.user.username = :x and a.nature like :y or a.lieu like :y or a.ville like :y order by a.dteStrt ASC")
	public Page<Activity> findMyActivitiesByMc(@Param("x")String username,@Param("y")String motCle,Pageable pageable);
	
	@Query("select a from Activity a where a.user.username = :x and a.nature like :y or a.lieu like :y or a.ville like :y order by a.dteStrt ASC")
	public Page<Activity> findAllyActivitiesByMc(@Param("y")String motCle,Pageable pageable);
	
	@Query("select a from Activity a where a.user.username = :x and (a.dteStrt between :y and :z or a.dteEnd between :y and :z) and a.nature != 'Congé' and a.statut = true")
	public List<Activity> findActivityBetween(@Param("x")String username,@Param("y")Date dteStrt,@Param("z") Date dteEnd);
	
	@Query("select a from Activity a where a.id = :x")
	public Activity findActivity(@Param("x") Long id);
	
	@Query(value="select * from Activity a where a.user_username = :x and CONVERT(Date,a.dte_Strt) >= :y and CONVERT(Date,a.dte_End) <= :z",nativeQuery=true)
	public List<Activity> findAllMyActivitiesByDates(@Param("x")String username,@Param("y")Date dateDebut,@Param("z")Date dateFin);
	
	
	@Query(value="select * from Activity a where a.user_username = :x and CONVERT(Date,a.dte_Strt) = :y or CONVERT(Date,a.dte_End) = :z",nativeQuery=true)
	public List<Activity> findAllMyActivitiesByDatesForDay(@Param("x")String username,@Param("y")Date dateDebut,@Param("z")Date dateFin);

}
