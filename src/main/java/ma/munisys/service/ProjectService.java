package ma.munisys.service;

import java.util.List;

import ma.munisys.entities.AppProfile;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.Authorisation;
import ma.munisys.entities.Project;

public interface ProjectService {
	
	public List<Project> findProjectByCustomer(String codeCustomer);
	

}
