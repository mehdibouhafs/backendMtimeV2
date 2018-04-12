package ma.munisys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import ma.munisys.entities.Activity;


public interface ActivityService {
	
	public Activity saveActivity(Activity activity);
	
	public Activity findActivityById(Long id);
	
	public Page<Activity> getByPage(int page,int size);
	
	public List<Activity> findAll();
	
	public Activity updateActivity(@PathVariable Long id, Activity Activity);
	
	public void deleteActivity(Long id);
		

}
