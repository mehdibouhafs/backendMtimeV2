package ma.munisys.service;

import java.util.List;

import ma.munisys.entities.AppProfile;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.Authorisation;

public interface AccountService {
	
	public AppUser saveUser(AppUser appUser);
	
	public AppProfile saveProfile(AppProfile profile);
	
	public void addProfileToUser(String username,String profileName);
	
	public AppUser findUserByUsername(String username);
	
	public Authorisation saveAuthorisation(String authorisationName);
	
	public void addAuthorisationToProfile(String authorisation,String profileName);
	
	public void addAuthorisationToUser(String authorisation,String username);
	

}
