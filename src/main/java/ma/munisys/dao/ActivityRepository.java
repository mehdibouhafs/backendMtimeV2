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
import ma.munisys.model.DureeActiviteMois;


public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	@Query("select a from Activity a where a.user.username = :x order by a.dteStrt DESC")
	public Page<Activity> getUserActivities(@Param("x")String username,Pageable pageable);
	
	@Query("select a from Activity a  where a.user.username=:x and (a.nature like :y or a.lieu like :y or a.ville like :y or a.customer.name like :y or a.id IN ( select b from ActivityProject b where b.project.prjName like :y)) order by a.dteStrt DESC")
	public Page<Activity> findMyActivitiesByMc(@Param("x")String username,@Param("y")String motCle,Pageable pageable);
	
	@Query("select a from Activity a where a.user.username=:x and  (a.nature like :y or a.lieu like :y or a.ville like :y or a.customer.name like :y) and a.typeActivite in :type order by a.dteStrt DESC")
	public Page<Activity> findMyActivitiesByMcAndType(@Param("x")String username,@Param("y")String motCle,Pageable pageable, @Param("type") String[] typeSelected);
	
	@Query("select a from Activity a where (a.nature like :y or a.lieu like :y or a.ville like :y or a.customer.name like :y) order by a.dteStrt DESC")
	public Page<Activity> findAllActivitiesByMc(@Param("y")String motCle,Pageable pageable);
	
	@Query("select a from Activity a where a.typeActivite in :type and (a.nature like :y or a.lieu like :y or a.ville like :y or a.customer.name like :y) order by a.dteStrt DESC")
	public Page<Activity> findAllActivitiesByMcAndType(@Param("y")String motCle,Pageable pageable, @Param("type") String[] typeSelected);
	
	@Query("select a from Activity a where a.user.username=:x and (a.dteStrt between :y and :z or a.dteEnd between :y and :z or (a.dteStrt<=:y and a.dteEnd>=:z)) and a.typeActivite != 'Activité congé' and a.typeActivite != 'Activité assistance' and a.statut = true")
	public List<Activity> findActivityBetween(@Param("x")String username,@Param("y")Date dteStrt,@Param("z") Date dteEnd);
	
	
	@Query("select a.id from Activity a where a.user.service.id=:idService and a.createdAt >= :y and a.createdAt <=:z  and a.statut = true order by a.dteStrt ASC" )
	public List<Long> findActivityByServiceBetweenDateSaisie(@Param("idService") Long idService,@Param("y")Date dteStrt,@Param("z") Date dteEnd);
	
	@Query("select a.id from Activity a where a.user.username=:x and a.createdAt >= :y and a.createdAt <=:z  and a.statut = true order by a.dteStrt ASC" )
	public List<Long> findActivityBetweenDateSaisie(@Param("x")String username,@Param("y")Date dteStrt,@Param("z") Date dteEnd);
	

	@Query("select a from Activity a where a.user.service.id=:idService and a.createdAt >= :y and a.createdAt <=:z  and a.statut = true  order by a.dteStrt,a.user.username,a.typeActivite ASC" )
	public List<Activity> findActivityBetweenDateSaisieGrouped(@Param("idService") Long idService,@Param("y")Date dteStrt,@Param("z") Date dteEnd);
	
	
	@Query("select a.id from Activity a where a.user.username=:x and year(a.dteStrt) = year(:today) and a.statut = true order by a.dteStrt ASC" )
	public List<Long> findActivityByUserByYear(@Param("x")String username,@Param("today")Date today);
	
	@Query("select new ma.munisys.model.DureeActiviteMois(SUM(a.durtion),a.typeActivite,month(a.dteStrt)) from Activity a where a.user.username=:x and year(a.dteStrt) = year(:today) and a.statut = true group by a.typeActivite,month(a.dteStrt)" )
	public List<DureeActiviteMois> findActivityByUserByYear2(@Param("x")String username,@Param("today")Date today);
	
	
	@Query("select a from Activity a where a.id!=:w and a.user.username=:x and (a.dteStrt between :y and :z or a.dteEnd between :y and :z or (a.dteStrt<=:y and a.dteEnd>=:z)) and a.typeActivite != 'Activité congé' and a.typeActivite != 'Activité assistance' and a.statut = true")
	public List<Activity> findActivityBetween(@Param("w") Long id,@Param("x")String username,@Param("y")Date dteStrt,@Param("z") Date dteEnd);
	
	@Query("select a from Activity a where a.id = :x")
	public Activity findActivity(@Param("x") Long id);
	
	@Query("select a from Activity a where a.typeActivite!='Activité congé' and a.statut=true and a.user.username=:x and a.dteEnd=(select max(a.dteEnd) from Activity a where a.statut=true and a.user.username=:x)")
	public List<Activity> getLastActivity(@Param("x") String username);
	
	@Query("select a from Activity a where a.user.username=:x and a.typeActivite!='Activité congé' and year(a.dteStrt)=year(:today) and month(a.dteStrt)=month(:today) and day(a.dteStrt)=day(:today)")
	public List<Activity> getActivityToday(@Param("x") String username, @Param("today") Date today);
	
	@Query("select a from Activity a where a.user.username=:x and a.typeActivite!='Activité congé' and year(a.dteStrt)=year(:today) and month(a.dteStrt)=month(:today) and day(a.dteStrt)=day(:today)+1")
	public List<Activity> getActivityTomorrow(@Param("x") String username, @Param("today") Date today);
	
	@Query(value="select a from Activity a where a.user.username = :x and ((a.dteStrt between :y and :z) or (a.dteEnd between :y and :z) or (a.dteStrt<=:y and a.dteEnd>=:z)) ")
	public List<Activity> findAllMyActivitiesByDates(@Param("x")String username,@Param("y")Date dateDebut,@Param("z")Date dateFin);

	
	@Query(value="select a from Activity a where a.user.username = :x and ( (:y between a.dteStrt and a.dteEnd) or ( month(:y) = month(a.dteStrt) and year(:y) = year(a.dteStrt) and day(:y) = day(a.dteStrt) ) or ( month(:z) = month(a.dteEnd) and year(:z) = year(a.dteEnd) and day(:z) = day(a.dteEnd) ))")
	public List<Activity> findAllMyActivitiesByDatesForDay(@Param("x")String username,@Param("y")Date dateDebut,@Param("z")Date dateFin);

	
	@Query(value="select a from Activity a where a.dteStrt >= :y and a.dteEnd <= :z")
	public List<Activity> findAllActivitiesByDates(@Param("y")Date dateDebut,@Param("z")Date dateFin);

	
	@Query(value="select a from Activity a where year(a.dteStrt)=year(:y) and month(a.dteStrt)=month(:y) and day(a.dteStrt)=day(:y) or year(a.dteEnd)=year(:z) and month(a.dteEnd)=month(:z) and day(a.dteEnd)=day(:z)")
	public List<Activity> findAllActivitiesByDatesForDay(@Param("y")Date dateDebut,@Param("z")Date dateFin);
	
	@Query(value="select a from Activity a where a.user.service.id=:idService and a.user.username like :username and a.dteStrt >= :y and a.dteEnd <= :z")
	public List<Activity> findAllActivitiesByDatesAndService(@Param("idService") Long idService, @Param("username") String username, @Param("y")Date dateDebut,@Param("z")Date dateFin);

	
	@Query(value="select a from Activity a where a.user.service.id=:idService and a.user.username like :username and (year(a.dteStrt)=year(:y) and month(a.dteStrt)=month(:y) and day(a.dteStrt)=day(:y) or year(a.dteEnd)=year(:z) and month(a.dteEnd)=month(:z) and day(a.dteEnd)=day(:z))")
	public List<Activity> findAllActivitiesByDatesForDayAndService(@Param("idService") Long idService, @Param("username") String username, @Param("y")Date dateDebut,@Param("z")Date dateFin);

	@Query("select a from Activity a where a.user.username=:x and a.statut=false and a.dteStrt<=:today and a.typeActivite!='Activité congé' and (a.nature like :mc or a.lieu like :mc or a.ville like :mc) order by a.dteStrt DESC")
	public Page<Activity> getActivityToDo(@Param("x") String username, @Param("mc") String mc, @Param("today") Date today, Pageable pageable);
	
	@Query("select a from Activity a where a.user.username=:x and a.typeActivite='Activité congé' order by a.dteStrt DESC")
	public Page<Activity> getMyActivityHoliday(@Param("x") String username, Pageable pageable);
	
	@Query("select a from Activity a where a.typeActivite='Activité support' and a.statut=true and a.request.rqtExcde=:x")
	public Page<Activity> getActivityRequestByTicket(@Param("x") String rqtExcde, Pageable pageable);
	
	@Query("select a from Activity a where a.user.service.id = :idService and a.user.username like :username and (a.nature like :y or a.lieu like :y or a.ville like :y) order by a.dteStrt DESC")
	public Page<Activity> getActivityByService(@Param("idService") Long idService, @Param("username") String username, @Param("y") String mc, Pageable pageable);

	@Query("select a from Activity a where a.user.service.id = :idService and a.user.username like :username and (a.nature like :y or a.lieu like :y or a.ville like :y) and a.typeActivite in :type order by a.dteStrt DESC")
	public Page<Activity> getActivityByServiceAndType(@Param("idService") Long idService, @Param("username") String username, @Param("y") String mc, Pageable pageable, @Param("type") String[] typeSelected);

	@Query("select a from Activity a where a.typeActivite='Activité projet' and a.user.username=:username and (a.nature like :mc or a.lieu like :mc or a.ville like :mc) order by a.dteStrt DESC")
	public Page<Activity> findMyActivityProject(@Param("username") String username, @Param("mc") String mc, Pageable pageable);

	@Query("select a from Activity a where a.typeActivite='Activité projet' and a.user.username=:username and (year(a.dteStrt)=year(:start) and month(a.dteStrt)=month(:start) and day(a.dteStrt)=day(:start)) and (a.nature like :mc or a.lieu like :mc or a.ville like :mc) order by a.dteStrt DESC")
	public Page<Activity> findMyActivityProjectByDate(@Param("username") String username, @Param("start") Date start, @Param("mc") String mc, Pageable pageable);

	@Query("select a from Activity a where a.user.username like :username and a.createdBy.username='kchaoui' and (a.nature like :y or a.lieu like :y or a.ville like :y) order by a.dteStrt DESC")
	public Page<Activity> getActivityPlanifiedDirection(@Param("username") String username, @Param("y") String mc, Pageable pageable);

}
