package ma.munisys.web;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ma.munisys.entities.Activity;
import ma.munisys.model.DateUtils;
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
			 throw new RuntimeException("Vous ne pouvez pas réaliser deux activitées en meme temps à part des activités d'assistance");
		}
		return activityService.saveActivity(activity);
	}
	
	@RequestMapping(value="/findMyActivitiesByMc",method=RequestMethod.GET)
	public Page<Activity> findMyActivitiesByMc(@RequestParam(name="username")String username,@RequestParam(name="motCle",defaultValue="")String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size, @RequestParam(name="type", defaultValue="") List<String> typeSelected) {
		System.out.println("typeSelected"+typeSelected);
		return activityService.findMyActivitiesByMc(username,mc,page, size, typeSelected);
	}
	
	@RequestMapping(value="/findAllActivitiesByMc",method=RequestMethod.GET)
	public Page<Activity> findAllActivitiesByMc(@RequestParam(name="motCle",defaultValue="")String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size, @RequestParam(name="type", defaultValue="") List<String> typeSelected) {
		return activityService.findAllActivitiesByMc(mc,page, size, typeSelected);
	}

	@RequestMapping(value="/activities",method=RequestMethod.GET)
	public List<Activity> findAll() {
		return activityService.findAll();
	}
	
	@RequestMapping(value="/lastactivity",method=RequestMethod.GET)
	public Activity getLastActivity(@RequestParam(name="username") String username) {
		return activityService.getLastActivity(username);
	}
	
	@RequestMapping(value="/activitytoday",method=RequestMethod.GET)
	public List<Activity> getActivityToday(@RequestParam(name="username") String username) {
		return activityService.getActivityToday(username);
	}
	
	@RequestMapping(value="/activitytomorrow",method=RequestMethod.GET)
	public List<Activity> getActivityTomorrow(@RequestParam(name="username") String username) {
		return activityService.getActivityTomorrow(username);
	}
	
	
	@RequestMapping(value="/findAllMyActivitiesByDates",method=RequestMethod.GET)
	public List<Activity> findAllMyActivitiesByDates(@RequestParam(name="username")  String username,@RequestParam(name="dteStrt") @DateTimeFormat (pattern = "yyyy-MM-dd")   Date dateDebut,@RequestParam(name="dteEnd")@DateTimeFormat (pattern = "yyyy-MM-dd") Date dateFin) {
		return activityService.findAllMyActivitiesByDates(username, dateDebut, dateFin);
	}
	
	@RequestMapping(value="/findAllMyActivitiesByDatesForDay",method=RequestMethod.GET)
	public List<Activity> findAllMyActivitiesByDatesFoDay(@RequestParam(name="username")  String username,@RequestParam(name="dteStrt") @DateTimeFormat (pattern = "yyyy-MM-dd")   Date dateDebut,@RequestParam(name="dteEnd")@DateTimeFormat (pattern = "yyyy-MM-dd") Date dateFin) {
		return activityService.findAllMyActivitiesByDatesForDay(username, dateDebut, dateFin);
	}
	
	
	@RequestMapping(value="/findAllActivitiesByDates",method=RequestMethod.GET)
	public List<Activity> findAllActivitiesByDates(@RequestParam(name="dteStrt") @DateTimeFormat (pattern = "yyyy-MM-dd")   Date dateDebut,@RequestParam(name="dteEnd")@DateTimeFormat (pattern = "yyyy-MM-dd") Date dateFin) {
		return activityService.findAllActivitiesByDates(dateDebut, dateFin);
	}
	
	@RequestMapping(value="/findAllActivitiesByDatesForDay",method=RequestMethod.GET)
	public List<Activity> findAllActivitiesByDatesFoDay(@RequestParam(name="dteStrt") @DateTimeFormat (pattern = "yyyy-MM-dd")   Date dateDebut,@RequestParam(name="dteEnd")@DateTimeFormat (pattern = "yyyy-MM-dd") Date dateFin) {
		return activityService.findAllActivitiesByDatesForDay(dateDebut, dateFin);
	}
	
	@RequestMapping(value="/activityToDo",method=RequestMethod.GET)
	public Page<Activity> getActivityToDo(@RequestParam(name="username")String username, @RequestParam(name="motCle",defaultValue="")String mc, @RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return activityService.getActivityToDo(username, mc, page, size);
	}
	
	@RequestMapping(value="/myactivityholiday", method=RequestMethod.GET)
	public Page<Activity> getMyActivityHoliday(@RequestParam(name="username") String username, @RequestParam(name="page",defaultValue="1") int page,@RequestParam(name="size",defaultValue="5") int size) {
		return activityService.getMyActivityHoliday(username, page, size);
	}
	
	
}
