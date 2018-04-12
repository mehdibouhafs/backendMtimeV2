package ma.munisys.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.munisys.entities.Service;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterForm {
	
	private String username;
	
	private String password;
	
	private String repassword;
	
	private Service service;
	
	private String nom;
	
	private String prenom;

}
