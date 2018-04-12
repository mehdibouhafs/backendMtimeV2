package ma.munisys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ma.munisys.dao.ContactRepository;
import ma.munisys.dao.CustomerDao;
import ma.munisys.dao.DirectionDao;
import ma.munisys.dao.EditeurDao;
import ma.munisys.dao.FormationRepository;
import ma.munisys.dao.ProjectDao;
import ma.munisys.dao.ServiceDao;
import ma.munisys.dao.TaskRepository;
import ma.munisys.dao.TechnologieDao;
import ma.munisys.dao.TypeActivityRepository;
import ma.munisys.entities.AppProfile;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.Contact;
import ma.munisys.entities.Customer;
import ma.munisys.entities.Direction;
import ma.munisys.entities.Editeur;
import ma.munisys.entities.Formation;
import ma.munisys.entities.Project;
import ma.munisys.entities.Service;
import ma.munisys.entities.Task;
import ma.munisys.entities.Technologie;
import ma.munisys.entities.TypeActivity;
import ma.munisys.service.AccountService;

@SpringBootApplication
public class JwtSpringSecApplication implements CommandLineRunner {
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FormationRepository formationRepository;
	
	@Autowired
	private TechnologieDao technologieDao;
	
	@Autowired
	private EditeurDao editeurDao; 
	
	@Autowired
	private DirectionDao directionDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private TypeActivityRepository typeActivityRepository;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ProjectDao projectDao;

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecApplication.class, args);
	}
	
	
	// if password db BCYPT
	
	@Bean // ce bean sera utilise n'importe ou
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
		customerDao.save(new Customer("05959", "MUNISYS", null));
		customerDao.save(new Customer("05960", "ONCF", null));
		
		//new Project("prjID", "F5", "En cours", "05959", ,  "value systeme", 14);
		
		projectDao.save(new Project("prjID", "F5", "En cours", new Customer("05959", "MUNISYS", null), new Date(), "Mr Alouane", "Value Systeme", 10));
		projectDao.save(new Project("prjID2", "F6", "En cours", new Customer("05959", "MUNISYS", null), new Date(), "Mr Khalid", "Réseaux", 15));
		TypeActivity typeActivity = new TypeActivity();
		typeActivity.setType("Planifier");
		typeActivityRepository.save(typeActivity);
		
		TypeActivity typeActivity1 = new TypeActivity();
		typeActivity1.setType("Réaliser");
		typeActivityRepository.save(typeActivity1);
		
		Direction direction = new Direction();
		direction.setName("Service Client");
		directionDao.save(direction);
		
		Service service = new Service();
		service.setDirection(direction);
		service.setServName("Système d'information");
		serviceDao.save(service);
		
		 accountService.saveProfile(new AppProfile(null, "Salarie_SI", null));
		 accountService.saveAuthorisation("read_si_activity");
		 accountService.saveAuthorisation("write_si_activity");
		 accountService.saveAuthorisation("delete_si_activity");
		 
		 accountService.addAuthorisationToProfile("read_si_activity", "Salarie_SI");
		 accountService.addAuthorisationToProfile("write_si_activity", "Salarie_SI");
		 
		 
		accountService.saveUser(new AppUser("admin","admin", "admin", "1234",service,"mehdi.jpg"));
		accountService.saveUser(new AppUser("user","user","user", "1234",service,"mehdi.jpg"));
		accountService.saveUser(new AppUser("Mehdi","Bouhafs","mbouhafs", "Bouhafsmehdi2",service,"mehdi.jpg"));
		accountService.saveUser(new AppUser("Stage","01","stage01", "123456",service,"mehdi.jpg"));
		
		accountService.addProfileToUser("mbouhafs", "Salarie_SI");
		accountService.addAuthorisationToUser("delete_si_activity", "mbouhafs");
		
		AppUser user = accountService.findUserByUsername("mbouhafs");
		System.out.println("User " + user.toString());

		
		Stream.of("T1","T2","T3").forEach(t->{
			taskRepository.save(new Task(null, t));
		});
		
		taskRepository.findAll().forEach(t->{
			System.out.println(t.getTaskName());
		});
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
		for(int i=1;i<6;i++) {
	        formationRepository.save(new Formation(null, "Formation KIMOCE", df.parse("01/03/2018"), df.parse("10/03/2018"), true));
	        formationRepository.save(new Formation(null, "Formation SAP", df.parse("01/04/2018"), df.parse("10/04/2019"), true));
		}
		formationRepository.save(new Formation(null, "Formation SAP", df.parse("01/04/2018"), df.parse("10/04/2019"), true));
		
		Editeur editeur = new Editeur();
		editeur.setNomEditeur("Oracle");
		editeurDao.save(editeur);
		
		Editeur editeur1 = new Editeur();
		editeur1.setNomEditeur("CISCO");
		editeurDao.save(editeur1);
		
		Technologie technologie = new Technologie();
		technologie.setNomTechnologie("JAVA EE");
		technologie.setEditeur(editeur);
		technologieDao.save(technologie);
        
        
		
	}
}
