package ma.munisys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.munisys.entities.Project;
import ma.munisys.service.ProjectService;

@RestController
public class ProjectRestController {
	
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value="/projectsByCustomer",method=RequestMethod.GET)
	public List<Project> findProjectByCustomer(@RequestParam(name="codeCustomer",defaultValue="")String codeCustomer) {
		return projectService.findProjectByCustomer(codeCustomer);
	}

}
