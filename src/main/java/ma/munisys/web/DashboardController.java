package ma.munisys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.munisys.model.StatisticActivities;
import ma.munisys.service.DashboardService;

@RestController
@CrossOrigin(origins="*")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping(value="/getStatistics", method=RequestMethod.GET)
	public StatisticActivities getStatistics(@RequestParam(name="username") String username) {
		return dashboardService.getStatisitics(username);
	}
	
}
