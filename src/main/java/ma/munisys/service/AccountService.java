package ma.munisys.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import ma.munisys.entities.AppProfile;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.Authorisation;
import ma.munisys.entities.Formation;

public interface AccountService {
	
	public List<AppUser> allusers();
	
	public AppUser saveUser(AppUser appUser);
	
	public AppProfile saveProfile(AppProfile profile);
	
	public void addProfileToUser(String username,String profileName);
	
	public AppUser findUserByUsername(String username);
	
	public Authorisation saveAuthorisation(String authorisationName);
	
	public void addAuthorisationToProfile(String authorisation,String profileName);
	
	public void addAuthorisationToUser(String authorisation,String username);
	
	public Page<Formation> findMyFormation(String username, String mc, int page ,int size);
	

}
