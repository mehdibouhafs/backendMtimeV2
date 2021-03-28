package ma.munisys.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.munisys.entities.Certification;
import ma.munisys.entities.EmpCertification;
import ma.munisys.entities.Formation;
import ma.munisys.model.Notification;
import ma.munisys.service.CertificationService;
import ma.munisys.service.NotificationService;


@RestController
@CrossOrigin(origins="*")
public class CertificationController {
	
	@Autowired
	private CertificationService certificationService;
	
	@RequestMapping(value="/certifications/{id}",method=RequestMethod.GET)
	public Certification findcertificationById(@PathVariable("id") Long id) {
		return certificationService.findCertificationById(id);
	}
	
	@RequestMapping(value="/certifications/{id}",method=RequestMethod.PUT)
	public Certification updatecertification(@PathVariable("id")  Long id,@RequestBody Certification certification) {
		return certificationService.updateCertification(id, certification);
	}
	
	@RequestMapping(value="/certifications/{id}",method=RequestMethod.DELETE)
	public void deletecertification(@PathVariable("id") Long id) {
		certificationService.deleteCertification(id);
	}

	@PostMapping(value="/certifications")
	public Certification savecertification(@RequestBody Certification certification) {
		
		return certificationService.saveCertification(certification);
	}
	
	@RequestMapping(value="/findCertifications",method=RequestMethod.GET)
	public Page<Certification> getByPage(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return certificationService.getByPage(mc, page, size);
	}

	@RequestMapping(value="/certifications",method=RequestMethod.GET)
	public List<Certification> findAll() {
		return certificationService.findAll();
	}
	
	@RequestMapping(value="/findMyCertifications",method=RequestMethod.GET)
	public Page<Certification> getMyCertifications(@RequestParam(name="username") String username,@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		
		return certificationService.getMyCertifications(username, mc, page, size);
	}
	
	@RequestMapping(value="/getCertificationToValide",method=RequestMethod.GET)
	public Page<Certification> getCertificationToValide(@RequestParam(name="idService") Long idService, @RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return certificationService.getCertificationToValide(idService, page, size);
	}
	
	@RequestMapping(value="/getCertificationThisMonth",method=RequestMethod.GET)
	public List<Certification> getCertificationThisMonth(@RequestParam(name="username") String username) {
		return certificationService.getCertificationThisMonth(username);
	}
	
	@RequestMapping(value="/getCertificationNextMonth",method=RequestMethod.GET)
	public List<Certification> getCertificationNextMonth(@RequestParam(name="username") String username) {
		return certificationService.getCertificationNextMonth(username);
	}
	
	@PostMapping(value="/outlookCertifications")
	public String addFormationToOutlook(@RequestBody Certification certification) {
		return certificationService.addCertificationToOutlook(certification);
	}
	
}
