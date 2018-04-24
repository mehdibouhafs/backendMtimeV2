package ma.munisys.web;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.munisys.entities.AppUser;
import ma.munisys.entities.Formation;
import ma.munisys.service.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/allusers")
	public List<AppUser> allusers() {
		return accountService.allusers();
	}
	
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
	
	@RequestMapping(value="/myformations", method=RequestMethod.GET)
	public Page<Formation> findMyFormation(@RequestParam String username, @RequestParam(name="mc",defaultValue="") String mc,@RequestParam(name="page",defaultValue="1")int page,@RequestParam(name="size",defaultValue="5")int size) {
		return accountService.findMyFormation(username, mc, page, size);
		
	}

}
