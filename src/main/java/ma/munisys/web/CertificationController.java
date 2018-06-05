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
import ma.munisys.entities.Certification;
import ma.munisys.model.Notification;
import ma.munisys.service.CertificationService;
import ma.munisys.service.NotificationService;


@RestController
@CrossOrigin(origins="*")
public class CertificationController {
	
	@Autowired
	private CertificationService certificationService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	/**
	   * POST  /some-action  -> do an action.
	   * 
	   * After the action is performed will be notified UserA.
	   */
	  @RequestMapping(value = "/some-action", method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<?> someAction() {

	    // Do an action here
	    // ...
	    
	    // Send the notification to "UserA" (by username)
		  messagingTemplate.convertAndSendToUser(
			      "mbouhafs", 
			      "/topic/greetings", 
			      new Notification("teste mbouhafs")
			    );
	    
	    // Return an http 200 status code
	    return new ResponseEntity<>(HttpStatus.OK);
	  }
	  
	  
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
		return certificationService.getByPage("%"+mc+"%", page, size);
	}

	@RequestMapping(value="/certifications",method=RequestMethod.GET)
	public List<Certification> findAll() {
		return certificationService.findAll();
	}
}
