package ma.munisys.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import ma.munisys.entities.Activity;


public interface ActivityService {
	
	public Activity saveActivity(Activity activity);
	
	public Activity findActivityById(Long id);
	
	public Page<Activity> findMyActivitiesByMc(String username,String mc,int page,int size);
	
	public Page<Activity> findAllActivitiesByMc(String mc,int page,int size);
	
	public List<Activity> findAll();
	
	public Activity updateActivity(@PathVariable Long id, Activity Activity);
	
	public void deleteActivity(Long id);
	
	
	 public List<Activity> findActivityBetween(String username,Date dteStrt,Date dteEnd);
	
	 public Page<Activity> getUserActivities(String username,int page,int size);
	 
	 public List<Activity> findAllMyActivitiesByDates(String username,Date dateDebut,Date dateFin);
	 
	 public List<Activity> findAllMyActivitiesByDatesForDay(String username,Date dateDebut,Date dateFin);
	 
	 

}
