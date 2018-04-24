package ma.munisys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import ma.munisys.entities.Request;

public interface RequestService {
	

	
	public Request findRequestById(String rqtExcde);
	
	public Page<Request> getByPage(String mc,int page,int size);
	
	public List<Request> findAll();
	
		
	
}
