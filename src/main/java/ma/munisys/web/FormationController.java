package ma.munisys.web;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import ma.munisys.entities.Formation;
import ma.munisys.service.FormationService;

@RestController
@CrossOrigin(origins="*")
public class FormationController {
	
	@Autowired
	private FormationService formationService;
	
	@RequestMapping(value="/formations/{id}",method=RequestMethod.GET)
	public Formation findFormationById(@PathVariable("id") Long id) {
		
		return formationService.findFormationById(id);
	}
	@RequestMapping(value="/formations/{id}",method=RequestMethod.PUT)
	public Formation updateFormation(@PathVariable("id")  Long id,@RequestBody Formation formation) {
		return formationService.updateFormation(id, formation);
	}
	
	@RequestMapping(value="/formations/{id}",method=RequestMethod.DELETE)
	public void deleteFormation(@PathVariable("id") Long id) {
		formationService.deleteFormation(id);
	}

	@PostMapping(value="/formations")
	public Formation saveFormation(@RequestBody Formation formation) {
		
		Long id = formationService.saveFormation(formation).getId();
		System.out.println(this.findFormationById(id).getParticipants().toString());
		
		return this.findFormationById(id);
	}
	
	@RequestMapping(value="/findFormations",method=RequestMethod.GET)
	public Page<Formation> getByPage(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return formationService.getByPage("%"+mc+"%", page, size);
	}

	@RequestMapping(value="/formations",method=RequestMethod.GET)
	public List<Formation> findAll() {
		return formationService.findAll();
	}
	
	
	
}
