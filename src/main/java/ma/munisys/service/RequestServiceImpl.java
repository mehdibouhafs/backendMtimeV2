package ma.munisys.service;

import java.util.List;
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

	

}
