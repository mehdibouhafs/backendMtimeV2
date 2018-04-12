package ma.munisys.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.munisys.entities.AppUser;
import ma.munisys.service.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRepassword())) throw new RuntimeException("You must confirm your password");
		
		AppUser user = accountService.findUserByUsername(userForm.getUsername());
		if(user !=null) throw new RuntimeException("This user already exists");
		AppUser appUser = new AppUser(userForm.getPrenom(),userForm.getNom(),userForm.getUsername(),userForm.getPassword(),userForm.getService(),null);
		
		accountService.saveUser(appUser);
		accountService.addProfileToUser(appUser.getUsername(),"Salarie_SI");
		return appUser;
	}
	

}
