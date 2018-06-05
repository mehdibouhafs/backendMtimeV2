package ma.munisys.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ma.munisys.dao.ActivityRepository;
import ma.munisys.entities.Activity;

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
	public Page<Activity> findMyActivitiesByMc(String username,String mc,int page, int size, List<String> typeSelected) {
		if(typeSelected.size() == 0) 
			return activityRepository.findMyActivitiesByMc(username,"%"+mc+"%",new PageRequest(page-1, size));
		else {
			
			List<String> typeOfActivity = new ArrayList<String>();
			for (String type : typeSelected) {
				switch(type) {
					case "AP":
						typeOfActivity.add("Activité projet");
						break;
					case "AS":
						typeOfActivity.add("Activité support");
						break;
					case "ASSI":
						typeOfActivity.add("Activité SI");
						break;
					case "ACM":
						typeOfActivity.add("Activité commerciale");
						break;
					case "AC":
						typeOfActivity.add("Activité congé");
						break;
					case "AR":
						typeOfActivity.add("Activité recouvrement");
						break;
				}
			}
			System.out.println(typeOfActivity.toString());
			String[] table = new String[typeOfActivity.size()];
			table = typeOfActivity.toArray(table);
			return activityRepository.findMyActivitiesByMcAndType(username,"%"+mc+"%",new PageRequest(page-1, size), table);
		}
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
	public Page<Activity> findAllActivitiesByMc(String mc, int page, int size, List<String> typeSelected) {
		// TODO Auto-generated method stub
		if(typeSelected.size() == 0) {
			return activityRepository.findAllActivitiesByMc("%"+mc+"%", new PageRequest(page-1, size));
		}
		
		else {
			List<String> typeOfActivity = new ArrayList<String>();
			for (String type : typeSelected) {
				switch(type) {
					case "AP":
						typeOfActivity.add("Activité projet");
						break;
					case "AS":
						typeOfActivity.add("Activité support");
						break;
					case "ASSI":
						typeOfActivity.add("Activité SI");
						break;
					case "ACM":
						typeOfActivity.add("Activité commerciale");
						break;
					case "AC":
						typeOfActivity.add("Activité congé");
						break;
					case "AR":
						typeOfActivity.add("Activité recouvrement");
						break;
				}
			}
			System.out.println(typeOfActivity.toString());
			String[] table = new String[typeOfActivity.size()];
			table = typeOfActivity.toArray(table);
			return activityRepository.findAllActivitiesByMcAndType("%"+mc+"%", new PageRequest(page-1, size), table);
		}
	}

	@Override
	public Activity getLastActivity(String username) {
		// TODO Auto-generated method stub
		return activityRepository.getLastActivity(username);
	}

	@Override
	public List<Activity> getActivityToday(String username) {
		
		return activityRepository.getActivityToday(username, new Date());
	}
	
	@Override
	public List<Activity> getActivityTomorrow(String username) {
		
		return activityRepository.getActivityTomorrow(username, new Date());
	}
	
	@Override
	public List<Activity> findAllMyActivitiesByDates(String username, Date dateDebut, Date dateFin) {
		return activityRepository.findAllMyActivitiesByDates(username, dateDebut, dateFin);
	}

	@Override
	public List<Activity> findAllMyActivitiesByDatesForDay(String username, Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return activityRepository.findAllMyActivitiesByDatesForDay(username, dateDebut, dateFin);
	}
	
	@Override
	public List<Activity> findAllActivitiesByDates(Date dateDebut, Date dateFin) {
		return activityRepository.findAllActivitiesByDates(dateDebut, dateFin);
	}

	@Override
	public List<Activity> findAllActivitiesByDatesForDay(Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return activityRepository.findAllActivitiesByDatesForDay(dateDebut, dateFin);
	}

	@Override
	public Page<Activity> getActivityToDo(String username, String mc, int page, int size) {
		// TODO Auto-generated method stub
		return activityRepository.getActivityToDo(username, "%"+mc+"%", new Date(), new PageRequest(page-1, size));
	}

	@Override
	public Page<Activity> getMyActivityHoliday(String username, int page, int size) {
		// TODO Auto-generated method stub
		return activityRepository.getMyActivityHoliday(username, new PageRequest(page-1, size));
	}

}
