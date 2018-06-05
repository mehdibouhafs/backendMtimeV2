package ma.munisys.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Activity;


public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	@Query("select a from Activity a where a.user.username = :x order by a.dteStrt DESC")
	public Page<Activity> getUserActivities(@Param("x")String username,Pageable pageable);
	
	@Query("select a from Activity a where a.user.username=:x and (a.nature like :y or a.lieu like :y or a.ville like :y) order by a.dteStrt DESC")
	public Page<Activity> findMyActivitiesByMc(@Param("x")String username,@Param("y")String motCle,Pageable pageable);
	
	@Query("select a from Activity a where a.user.username=:x and (a.nature like :y or a.lieu like :y or a.ville like :y) and a.typeActivite in :type order by a.dteStrt DESC")
	public Page<Activity> findMyActivitiesByMcAndType(@Param("x")String username,@Param("y")String motCle,Pageable pageable, @Param("type") String[] typeSelected);
	
	@Query("select a from Activity a where a.nature like :y or a.lieu like :y or a.ville like :y order by a.dteStrt DESC")
	public Page<Activity> findAllActivitiesByMc(@Param("y")String motCle,Pageable pageable);
	
	@Query("select a from Activity a where a.typeActivite in :type and (a.nature like :y or a.lieu like :y or a.ville like :y) order by a.dteStrt DESC")
	public Page<Activity> findAllActivitiesByMcAndType(@Param("y")String motCle,Pageable pageable, @Param("type") String[] typeSelected);
	
	@Query("select a from Activity a where a.user.username = :x and (a.dteStrt between :y and :z or a.dteEnd between :y and :z) and a.typeActivite != 'Activité congé' and a.typeActivite != 'Activité assistance' and a.statut = true")
	public List<Activity> findActivityBetween(@Param("x")String username,@Param("y")Date dteStrt,@Param("z") Date dteEnd);
	
	@Query("select a from Activity a where a.id = :x")
	public Activity findActivity(@Param("x") Long id);
	
	@Query("select a from Activity a where a.statut=true and a.user.username=:x and a.dteEnd=(select max(a.dteEnd) from Activity a where a.statut=true and a.user.username=:x)")
	public Activity getLastActivity(@Param("x") String username);
	
	@Query("select a from Activity a where a.user.username=:x and year(a.dteStrt)=year(:today) and month(a.dteStrt)=month(:today) and day(a.dteStrt)=day(:today)")
	public List<Activity> getActivityToday(@Param("x") String username, @Param("today") Date today);
	
	@Query("select a from Activity a where a.user.username=:x and a.typeActivite!='Activité congé' and year(a.dteStrt)=year(:today) and month(a.dteStrt)=month(:today) and day(a.dteStrt)=day(:today)+1")
	public List<Activity> getActivityTomorrow(@Param("x") String username, @Param("today") Date today);
	
	@Query(value="select a from Activity a where a.user.username = :x and a.dteStrt >= :y and a.dteEnd <= :z")
	public List<Activity> findAllMyActivitiesByDates(@Param("x")String username,@Param("y")Date dateDebut,@Param("z")Date dateFin);

	
	@Query(value="select a from Activity a where a.user.username = :x and year(a.dteStrt)=year(:y) and month(a.dteStrt)=month(:y) and day(a.dteStrt)=day(:y) or year(a.dteEnd)=year(:z) and month(a.dteEnd)=month(:z) and day(a.dteEnd)=day(:z)")
	public List<Activity> findAllMyActivitiesByDatesForDay(@Param("x")String username,@Param("y")Date dateDebut,@Param("z")Date dateFin);

	
	@Query(value="select a from Activity a where a.dteStrt >= :y and a.dteEnd <= :z")
	public List<Activity> findAllActivitiesByDates(@Param("y")Date dateDebut,@Param("z")Date dateFin);

	
	@Query(value="select a from Activity a where year(a.dteStrt)=year(:y) and month(a.dteStrt)=month(:y) and day(a.dteStrt)=day(:y) or year(a.dteEnd)=year(:z) and month(a.dteEnd)=month(:z) and day(a.dteEnd)=day(:z)")
	public List<Activity> findAllActivitiesByDatesForDay(@Param("y")Date dateDebut,@Param("z")Date dateFin);

	@Query("select a from Activity a where a.user.username=:x and a.statut=false and a.dteStrt<=:today and a.typeActivite!='Activité congé' and (a.nature like :mc or a.lieu like :mc or a.ville like :mc) order by a.dteStrt DESC")
	public Page<Activity> getActivityToDo(@Param("x") String username, @Param("mc") String mc, @Param("today") Date today, Pageable pageable);
	
	@Query("select a from Activity a where a.user.username=:x and a.typeActivite='Activité congé' order by a.dteStrt DESC")
	public Page<Activity> getMyActivityHoliday(@Param("x") String username, Pageable pageable);
}
