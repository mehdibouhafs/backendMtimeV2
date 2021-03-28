package ma.munisys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import ma.munisys.entities.Request;

public interface RequestService {
	

	
	public Request findRequestById(String rqtExcde);
	
	public Page<Request> getByPage(String mc,int page,int size);
	
	public List<Request> findAll();
	
	public Page<Request> getmyrequests(String username,String mc,int page,int size);
	
	public Page<Request> getrequestsGroupe(Long id,String mc,int page,int size);
	
	public List<Request> getTicketByCustomerAndService(String codeClient,String serviceName);
	
	public List<Request> getTicketByCustomerAndServiceAndNature( String codeClient , String service,String nature);
	
	
}
