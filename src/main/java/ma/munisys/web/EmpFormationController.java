package ma.munisys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ma.munisys.entities.EmpFormation;
import ma.munisys.service.IEmpFormationService;

@RestController
@CrossOrigin(origins="*")
public class EmpFormationController {
	
	@Autowired
	IEmpFormationService empFormationService;
	
	@RequestMapping(value="/validFormation", method=RequestMethod.PUT)
	public EmpFormation validFormation(@RequestBody EmpFormation empFormation) {
		return empFormationService.validFormation(empFormation);
	}
	
}
