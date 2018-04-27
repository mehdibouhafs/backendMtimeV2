package ma.munisys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ma.munisys.dao.ActivityRepository;
import ma.munisys.entities.Activity;
import ma.munisys.entities.ActivityProject;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity saveActivity(Activity activity) {
		Activity a =  activityRepository.saveAndFlush(activity);
		Long id = a.getId();
		System.out.println("findActivity(id) " + activityRepository.findActivity(id));
		return a;
	}

	@Override
	public Activity findActivityById(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public Page<Activity> findMyActivitiesByMc(String username,String mc,int page, int size) {
		return activityRepository.findMyActivitiesByMc(username,"%"+mc+"%",new PageRequest(page-1, size));
	}

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

	@Override
	public Activity updateActivity(Long id, Activity activity) {
		activity.setId(id);
		return activityRepository.save(activity);
	}

	@Override
	public void deleteActivity(Long id) {
	
		activityRepository.delete(id);
	}

	@Override
	public List<Activity> findActivityBetween(String username, Date dteStrt, Date dteEnd) {
		// TODO Auto-generated method stub
		return activityRepository.findActivityBetween(username, dteStrt, dteEnd);
	}

	@Override
	public Page<Activity> getUserActivities(String username, int page,int size) {
		// TODO Auto-generated method stub
		return activityRepository.getUserActivities(username, new PageRequest(page-1, size));
	}

	@Override
	public Page<Activity> findAllActivitiesByMc(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return activityRepository.findAllyActivitiesByMc("%"+mc+"%", new PageRequest(page-1, size));
	}

	@Override
	public List<Activity> findAllMyActivitiesByDates(String username, Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return activityRepository.findAllMyActivitiesByDates(username, dateDebut, dateFin);
	}

	@Override
	public List<Activity> findAllMyActivitiesByDatesForDay(String username, Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return activityRepository.findAllMyActivitiesByDatesForDay(username, dateDebut, dateFin);
	}

}
