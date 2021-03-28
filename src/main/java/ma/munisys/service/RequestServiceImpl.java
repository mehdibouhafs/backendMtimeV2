package ma.munisys.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ma.munisys.dao.RequestRepository;
import ma.munisys.entities.Request;

@Service
public class RequestServiceImpl implements RequestService  {
	
	@Autowired
	private RequestRepository requestRepository;

	@Override
	public Request findRequestById(String rqtExcde) {
		// TODO Auto-generated method stub
		return requestRepository.findOne(rqtExcde);
	}

	@Override
	public Page<Request> getByPage(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return requestRepository.chercher("%"+mc+"%", new PageRequest(page, size));
	}

	@Override
	public List<Request> findAll() {
		// TODO Auto-generated method stub
		return requestRepository.findAll();
	}

	@Override
	public Page<Request> getmyrequests(String username, String mc, int page, int size) {
		return requestRepository.mytickets(username, "%"+mc+"%", new PageRequest(page-1, size));
	}

	@Override
	public Page<Request> getrequestsGroupe(Long id, String mc, int page, int size) {
		
		return requestRepository.ticketsGroupe(id, mc, new PageRequest(page-1, size));
	}

	@Override
	public List<Request> getTicketByCustomerAndService(String codeClient,String serviceName) {
		
		return requestRepository.getTicketByCustomerAndService(codeClient,serviceName);
	}

	@Override
	public List<Request> getTicketByCustomerAndServiceAndNature(String codeClient, String service, String nature) {
		// TODO Auto-generated method stub
		return requestRepository.getTicketByCustomerAndServiceAndNature(codeClient, service, nature);
	}

	

}
