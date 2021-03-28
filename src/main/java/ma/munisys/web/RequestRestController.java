package ma.munisys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ma.munisys.entities.Request;
import ma.munisys.service.RequestService;

@RestController
@CrossOrigin(origins="*")
public class RequestRestController {
	
	@Autowired
	private RequestService requestService;
	
	@RequestMapping(value="/requests/{rqtExcde}",method=RequestMethod.GET)
	public Request findFormationById(@PathVariable("rqtExcde") String rqtExcde) {
		Request request = requestService.findRequestById(rqtExcde);
		if(request==null) throw new RuntimeException("Demande introuvable !"); 
		return request;
	}
	
		
	@RequestMapping(value="/findRequests",method=RequestMethod.GET)
	public Page<Request> getByPage(@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return requestService.getByPage("%"+mc+"%", page, size);
	}

	@RequestMapping(value="/requests",method=RequestMethod.GET)
	public List<Request> findAll() {
		return requestService.findAll();
	}
	
	@RequestMapping(value="/myrequests",method=RequestMethod.GET)
	public Page<Request> findmyrequests(@RequestParam(name="username") String username,@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return requestService.getmyrequests(username, mc, page, size);
	}
	
	@RequestMapping(value="/requests-groupe",method=RequestMethod.GET)
	public Page<Request> findrequestsGroupe(@RequestParam(name="idService") Long id,@RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return requestService.getrequestsGroupe(id, mc, page, size);
	}
	
	@RequestMapping(value="/requests/customer/{codeClient}/service/{serviceName}",method=RequestMethod.GET)
	public List<Request> findRequestByCustomerCode(@PathVariable("codeClient") String codeClient,@PathVariable("serviceName") String serviceName) {
		List<Request> requests = requestService.getTicketByCustomerAndService(codeClient,serviceName);
		if(requests==null || requests.isEmpty()) throw new RuntimeException("Demandes introuvable pour le client : "+ codeClient +" service : "+serviceName); 
		return requests;
	}
	
	@RequestMapping(value="/requestsByNature/customer/{codeClient}/service/{serviceName}",method=RequestMethod.GET)
	public List<Request> findRequestByCustomerCodeAndNature(@PathVariable("codeClient") String codeClient,@PathVariable("serviceName") String serviceName) {
		List<Request> requests = requestService.getTicketByCustomerAndServiceAndNature(codeClient, serviceName, "Maintenance préventive");
		if(requests==null || requests.isEmpty()) throw new RuntimeException("Demandes introuvable pour le client : "+ codeClient +" service : "+serviceName + " nature : PM" ); 
		return requests;
	}
}
