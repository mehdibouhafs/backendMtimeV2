package ma.munisys.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ma.munisys.entities.Activity;
import ma.munisys.service.ActivityService;

@RestController
@CrossOrigin(origins="*")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	
	@RequestMapping(value="/findMyActivities",method=RequestMethod.GET)
	public Page<Activity> getUserActivities(@RequestParam(name="username") String username, @RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return activityService.getUserActivities(username, page,size);
	}
	
	@RequestMapping(value="/activities/{id}",method=RequestMethod.GET)
	public Activity findActivityById(@PathVariable("id") Long id) {
		
		return activityService.findActivityById(id);
	}
	@RequestMapping(value="/activities/{id}",method=RequestMethod.PUT)
	public Activity updateActivity(@PathVariable("id")  Long id,@RequestBody Activity activity) {
		return activityService.updateActivity(id, activity);
	}
	
	@RequestMapping(value="/activities/{id}",method=RequestMethod.DELETE)
	public void deleteActivity(@PathVariable("id") Long id) {
		activityService.deleteActivity(id);
	}

	@RequestMapping(value="/activities",method = RequestMethod.POST)
	public Activity saveactivity(@RequestBody Activity activity) {
		if(activityService.findActivityBetween(activity.getUser().getUsername(), activity.getDteStrt(), activity.getDteEnd()).size()>0) {
			 throw new RuntimeException("Vous ne pouvez pas réaliser deux activitées en meme temps");
		}
		return activityService.saveActivity(activity);
	}
	
	@RequestMapping(value="/findMyActivitiesByMc",method=RequestMethod.GET)
	public Page<Activity> findMyActivitiesByMc(@RequestParam(name="username")String username,@RequestParam(name="motCle",defaultValue="")String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return activityService.findMyActivitiesByMc(username,mc,page, size);
	}
	
	@RequestMapping(value="/findAllActivitiesByMc",method=RequestMethod.GET)
	public Page<Activity> findAllActivitiesByMc(@RequestParam(name="motCle",defaultValue="")String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return activityService.findAllActivitiesByMc(mc,page, size);
	}

	@RequestMapping(value="/activities",method=RequestMethod.GET)
	public List<Activity> findAll() {
		return activityService.findAll();
	}
}
