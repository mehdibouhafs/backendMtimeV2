package ma.munisys.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import ma.munisys.entities.Activity;

public interface ActivityService {

	public Activity saveActivity(Activity activity);

	public Activity findActivityById(Long id);

	public Page<Activity> findMyActivitiesByMc(String username, String mc, int page, int size, List<String> typeSelected);

	public Page<Activity> findAllActivitiesByMc(String mc, int page, int size, List<String> typeSelected);

	public List<Activity> findAll();

	public Activity updateActivity(@PathVariable Long id, Activity Activity);

	public void deleteActivity(Long id);

	public List<Activity> findActivityBetween(String username, Date dteStrt, Date dteEnd);

	public Page<Activity> getUserActivities(String username, int page, int size);

	public Activity getLastActivity(String username);

	public List<Activity> getActivityToday(String username);

	public List<Activity> getActivityTomorrow(String username);

	public List<Activity> findAllMyActivitiesByDates(String username, Date dateDebut, Date dateFin);

	public List<Activity> findAllMyActivitiesByDatesForDay(String username, Date dateDebut, Date dateFin);

	public List<Activity> findAllActivitiesByDates(Date dateDebut, Date dateFin);

	public List<Activity> findAllActivitiesByDatesForDay(Date dateDebut, Date dateFin);

	public Page<Activity> getActivityToDo(String username, String mc, int page, int size);
	
	public Page<Activity> getMyActivityHoliday(String username, int page, int size);
	
}
