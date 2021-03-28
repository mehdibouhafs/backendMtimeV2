package ma.munisys.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.munisys.entities.Objectif;
import ma.munisys.service.ObjectifService;

@RestController
@CrossOrigin(origins="*")
public class ObjectifController {
	
	@Autowired
	private ObjectifService objectifService;
	
	@RequestMapping(value="/save-objectif",method=RequestMethod.POST)
	public Objectif saveObjectif(@RequestBody Objectif objectif) {
		return objectifService.saveObjectif(objectif);
	}
	
	@RequestMapping(value="/all-objectifs", method=RequestMethod.GET)
	public Page<Objectif> getAllObjectifs(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="10")int size) {
		return objectifService.getAllObjectifs(mc, page, size);
	}
	
	@RequestMapping(value="/my-objectifs-valide", method=RequestMethod.GET)
	public List<Objectif> getMyObjectifsValide(@RequestParam("username") String username) {
		return objectifService.getMyObjectifsValide(username);
	}
	
	@RequestMapping(value="/update-taux", method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateTaux(@RequestParam("id") Long id, @RequestParam("taux") double taux) {
		objectifService.updateTaux(id, taux);
	}

}
