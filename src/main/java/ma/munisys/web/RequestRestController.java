package ma.munisys.web;

import java.util.List;

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
}
