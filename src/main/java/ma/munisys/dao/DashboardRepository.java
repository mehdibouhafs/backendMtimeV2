package ma.munisys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Activity;
import ma.munisys.model.ActivityParCustomer;
import ma.munisys.model.ActivityParType;
import ma.munisys.model.ActivityRealisedParMonth;
import ma.munisys.model.DurtionActivityParMonth;

public interface DashboardRepository extends JpaRepository<Activity, Long> {

	@Query("select count(a) from Activity a where a.user.username = :x and a.statut=true")
	public int getActivitiesRealised(@Param("x")String username);
	
	@Query("select count(a) from Activity a where a.user.username = :x and a.statut=false")
	public int getActivitiesPlanified(@Param("x")String username);
	
	@Query(value="select count(*) from activity where activity.user_username = :x and activity.statut=1 and activity.type_activite!='Activité congé' and (datepart(dw, activity.dte_strt) in (6,7) or datepart(dw, activity.dte_end) in (6,7))", nativeQuery=true)
	public int getActivitiesRealisedOnWeekEnd(@Param("x")String username);
	
	@Query(value="select count(*) from activity where activity.user_username = :x and activity.statut=1 and activity.type_activite!='Activité congé' and (datepart(dw, activity.dte_strt) in (6,7) and datepart(month, activity.dte_strt)=datepart(month, getdate()) and datepart(year, activity.dte_strt)=datepart(year, getdate()) or datepart(dw, activity.dte_end) in (6,7) and datepart(month, activity.dte_end)=datepart(month, getdate()) and datepart(year, activity.dte_end)=datepart(year, getdate()))", nativeQuery=true)
	public int getActivitiesRealisedOnWeekEndThisMonth(@Param("x")String username);
	
	@Query(value="select count(*) from activity where activity.user_username = :x and activity.statut=1 and (datepart(month, activity.dte_strt)=datepart(month, getdate()) and datepart(year, activity.dte_strt)=datepart(year, getdate()) or datepart(month, activity.dte_end)=datepart(month, getdate()) and datepart(year, activity.dte_end)=datepart(year, getdate()))", nativeQuery=true)
	public int getActivitiesRealisedOnthisMonth(@Param("x")String username);
	
	@Query(value="select count(*) from activity where activity.user_username = :x and activity.statut=0 and (datepart(month, activity.dte_strt)=datepart(month, getdate()) and datepart(year, activity.dte_strt)=datepart(year, getdate()) or datepart(month, activity.dte_end)=datepart(month, getdate()) and datepart(year, activity.dte_end)=datepart(year, getdate()))", nativeQuery=true)
	public int getActivitiesPlanifiedOnthisMonth(@Param("x")String username);
	
	@Query("select new ma.munisys.model.ActivityParType(a.typeActivite, count(a)) from Activity a where a.user.username=:x and a.statut=true group by a.typeActivite")
	public List<ActivityParType> getActivitiesParType(@Param("x")String username);
	
	@Query("select new ma.munisys.model.ActivityParCustomer(a.customer.name, count(a)) from Activity a where a.user.username=:x and a.statut=true and a.typeActivite!='Activité congé' and a.customer.name not like '%MUNISYS%' group by a.customer.name")
	public List<ActivityParCustomer> getNbreActivitiesParCustomer(@Param("x") String username);
	
	@Query("select coalesce(sum(a.durtion),0) from Activity a where a.user.username=:x and a.statut=true and a.typeActivite!='Activité congé'")
	public long getDurtionActivities(@Param("x") String username);
	
	@Query("select min(a.dteStrt) from Activity a where a.statut=true and a.user.username=:x")
	public Date getMinDate(@Param("x") String username);
	
	@Query(value="select coalesce(sum(activity.durtion),0) from activity where activity.user_username = :x and activity.statut=1 and activity.type_activite!='Activité congé' and (datepart(month, activity.dte_strt)=datepart(month, getdate()) and datepart(year, activity.dte_strt)=datepart(year, getdate()) or datepart(month, activity.dte_end)=datepart(month, getdate()) and datepart(year, activity.dte_end)=datepart(year, getdate()))", nativeQuery=true)
	public int getDurtionActivitiesOnthisMonth(@Param("x")String username);
	
	@Query("select new ma.munisys.model.DurtionActivityParMonth(month(a.dteStrt), year(a.dteStrt), coalesce(sum(a.durtion), 0)) from Activity a where a.user.username=:x and a.statut=true and a.typeActivite!='Activité congé' group by month(a.dteStrt), year(a.dteStrt) order by year(a.dteStrt), month(a.dteStrt) desc")
	public List<DurtionActivityParMonth> getDurtionParMonth(@Param("x") String username, Pageable page);
	
	
	@Query("select new ma.munisys.model.ActivityRealisedParMonth(month(a.dteStrt), year(a.dteStrt), count(a)) from Activity a where a.user.username=:x and a.statut=true and a.typeActivite!='Activité congé' group by month(a.dteStrt), year(a.dteStrt) order by year(a.dteStrt), month(a.dteStrt) desc")
	public List<ActivityRealisedParMonth> getNbreActivitiesParMonth(@Param("x") String username);

	
}
