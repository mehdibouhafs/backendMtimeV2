package ma.munisys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@PostMapping(value="/activities")
	public Activity saveactivity(@RequestBody Activity activity) {
		
		return activityService.saveActivity(activity);
	}
	
	@RequestMapping(value="/findactivities",method=RequestMethod.GET)
	public Page<Activity> getByPage(@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return activityService.getByPage(page, size);
	}

	@RequestMapping(value="/activities",method=RequestMethod.GET)
	public List<Activity> findAll() {
		return activityService.findAll();
	}
}
