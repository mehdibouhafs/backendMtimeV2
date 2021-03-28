package ma.munisys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ma.munisys.entities.EmpCertification;
import ma.munisys.service.IEmpCertificationService;

@RestController
@CrossOrigin(origins="*")
public class EmpCertificationController {
	
	@Autowired
	IEmpCertificationService empCertificationService;
	
	@RequestMapping(value="/empcertifications", method=RequestMethod.GET)
	public List<EmpCertification> findAll() {
		return empCertificationService.findAll();
	}
	
	@RequestMapping(value="/validCertification", method=RequestMethod.PUT)
	public EmpCertification validCertification(@RequestBody EmpCertification empCertification) {
		return empCertificationService.validCertification(empCertification);
	}

}
