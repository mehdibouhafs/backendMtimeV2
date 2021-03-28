package ma.munisys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ma.munisys.dao.ActivityRepository;
import ma.munisys.dao.CustomerDao;
import ma.munisys.dao.DirectionDao;
import ma.munisys.dao.EditeurDao;
import ma.munisys.dao.FormationRepository;
import ma.munisys.dao.ProjectDao;
import ma.munisys.dao.RequestRepository;
import ma.munisys.dao.ServiceDao;
import ma.munisys.dao.TaskRepository;
import ma.munisys.dao.TechnologieDao;
import ma.munisys.dao.TypeActivityRepository;
import ma.munisys.entities.Customer;
import ma.munisys.entities.Project;
import ma.munisys.service.AccountService;
import ma.munisys.service.EmailService;
import ma.munisys.service.IStorageService;
import ma.munisys.service.StorageServiceImpl;

@SpringBootApplication
@EnableScheduling
@Configuration
public class JwtSpringSecApplication implements CommandLineRunner {
	
	@Autowired
	private IStorageService storageService;
	private static IStorageService staticStorageService;
	@Autowired
	private StorageServiceImpl storageService2;
	
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
	private EmailService emailService;
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private  ProjectDao projectDao;
	
	private static ProjectDao staticProjectDao;
	
	private  static CustomerDao staticCustomerDao;
	
	private  static EmailService staticEmailService;
	
	@Autowired
	private ActivityRepository activityDao;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Value("${my.environnement.name}")
	private  String myEnvironnement;
	 private static String myStaticEnvironnement;
	
	@PostConstruct
	public void init() {
		this.staticProjectDao=projectDao;
		this.staticCustomerDao=customerDao;
		this.staticEmailService=emailService;
		this.staticStorageService=storageService;
		this.myStaticEnvironnement=myEnvironnement;

	}
	 

    

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecApplication.class, args);
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public static void loadFromCRM() {
		//LOGGER.debug("STARTING TASK Projetcts CRON ");
		// loadProjetsFromSap();
		loadAllCustomers();
		loadProjetsFromCRM();
		//LOGGER.debug("ENDING TASK Projects CRON ");
	}
	
	
	@Scheduled(cron = "0 0 7 * * MON")//MON
	public static void SendReports() {
		
		if(isProdEnvironnement()==true) {
			Date starteDate = getDateBeforeDay(7);
			Date endDate = new Date();
			staticEmailService.sendEtatWeekly(starteDate,endDate);
		}
		
	}
	/*
	@Scheduled(cron = "0 10 10 * * *")//MON
	public  void SendReportsDEv() {
		
		if(isProdEnvironnement()==false) {
			Date starteDate = getDateBeforeDay(7);
			Date endDate = new Date();
			staticEmailService.sendEtatWeeklyTest(starteDate,endDate);
		}
		
	}
	
	@Scheduled(cron = "0 40 10 * * *")//MON
	public  void SendReportsDEv2() {
		
		if(isProdEnvironnement()==false) {
			Date starteDate = getDateBeforeDay(7);
			Date endDate = new Date();
			staticEmailService.sendEtatWeeklyTest(starteDate,endDate);
		}
		
	}
	
	@Scheduled(cron = "0 10 11 * * *")//MON
	public  void SendReportsDEv3() {
		
		if(isProdEnvironnement()==false) {
			Date starteDate = getDateBeforeDay(7);
			Date endDate = new Date();
			staticEmailService.sendEtatWeeklyTest(starteDate,endDate);
		}
		
	}*/
	

	
	
	
	
	private static boolean isProdEnvironnement() {
		if(myStaticEnvironnement.equalsIgnoreCase("prod")) {
			return true;
		}else {
			return false;
		}
	}
	
	@javax.transaction.Transactional
	public static  void loadProjetsFromCRM() {
		
		//disable all project
		for(Project p : staticProjectDao.findAll()) {
			p.setDisabled(true);
			staticProjectDao.save(p);
		}
		
		 ResultSet rs1 = null;
		 Connection conn=null;
		try {
             String connectionUrl =
                     "jdbc:sqlserver://tripoli;"
                     + "database=PDC360;"
                     + "user=munitime;"
                     + "password=123456;"
                     + "encrypt=false;"
                     + "trustServerCertificate=false;"
                     + "loginTimeout=30;";
              conn = DriverManager.getConnection(connectionUrl);
                   Statement stmt = conn.createStatement();
            	 
           
            rs1 = stmt.executeQuery("SELECT code_projet, designation,client_code,closed FROM PDC360.dbo.projet"); 
            
            while (rs1.next()) {
            	 
            		  final Project p = new Project();
                      p.setDisabled(false);
                      p.setDteUpdtePrj(new Date());
                      if (rs1.getString(1) != null && !rs1.getString(1).equals("null")) {
                          p.setPrjId(rs1.getString(1));
                      }
                      
                      if (rs1.getString(2) != null && !rs1.getString(2).equals("null")) {
                          p.setPrjName(rs1.getString(2));
                      }
                     
                      p.setDisabled(false);
                      
                      if (rs1.getString(3) != null && !rs1.getString(3).equals("null")) {
                    	  System.out.println(rs1.getString(3));
                    	  Customer c = getCustomer(rs1.getString(3));
                    	  c.setDisabled(false);
                    	  if(c!=null) {
                    	
	                          staticCustomerDao.save(c); 
	                    	  p.setCustomer(c);
                    	  }
                      }
                      staticProjectDao.save(p);
                      }
            	 
                      }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if(rs1!=null) {
        		try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	if(conn!=null) {
	        	try {
	        		conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
		}
    }
	
	
	private static Customer getCustomer(String codeClient) {
		ResultSet rs1 = null;
		Customer customer=null;
		Connection conn=null;
		try {
			String connectionUrl =
                    "jdbc:sqlserver://tripoli;"
                    + "database=PDC360;"
                    + "user=munitime;"
                    + "password=123456;"
                    + "encrypt=false;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
             conn = DriverManager.getConnection(connectionUrl);
                  Statement stmt = conn.createStatement();
           
            rs1 = stmt.executeQuery("SELECT code,raison_sociale,actif FROM PDC360.dbo.partie_interessee where dtype='Client' and code ='"+codeClient+"'");
           
            while (rs1.next()) {
        		    customer = new Customer();
        		    customer.setCode(rs1.getString(1));
        		    customer.setName(rs1.getString(2));
        		    if(rs1.getString(3)!=null && rs1.getString(3).equals("1")) {
        		    	customer.setDisabled(true);
        		    }
        		    customer.setDisabled(false);
  
            	 }
                      }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if(rs1!=null) {
        		try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	if(conn!=null) {
	        	try {
	        		conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
		return customer;
	}
	
	@Transactional
	private static Customer loadAllCustomers() {
		
		for(Customer c : staticCustomerDao.findAll()) {
			c.setDisabled(true);
			staticCustomerDao.save(c);
		}
		
		ResultSet rs1 = null;
		Customer customer=null;
		Connection conn=null;
		try {
			String connectionUrl =
                    "jdbc:sqlserver://tripoli;"
                    + "database=PDC360;"
                    + "user=munitime;"
                    + "password=123456;"
                    + "encrypt=false;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
             conn = DriverManager.getConnection(connectionUrl);
                  Statement stmt = conn.createStatement();
           
            rs1 = stmt.executeQuery("SELECT code,raison_sociale,actif FROM PDC360.dbo.partie_interessee where dtype='Client'");
           
            while (rs1.next()) {
        		    customer = new Customer();
        		    customer.setCode(rs1.getString(1));
        		   customer.setName(rs1.getString(2));
        		    if(rs1.getString(3)!=null && rs1.getString(3).equals("1")) {
        		    	customer.setDisabled(true);
        		    }
        		    staticCustomerDao.save(customer);
            	 }
                      }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if(rs1!=null) {
        		try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	if(conn!=null) {
	        	try {
	        		conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
		return customer;
	}
	
	
	// if password db BCYPT
	
	@Bean // ce bean sera utilise n'importe ou
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		storageService.deleteAll();
		storageService.init();
		Date starteDate = getDateBeforeDay(7);
		Date endDate = new Date();
		//loadProjetsFromCRM();
		//staticEmailService.sendEtatWeeklyTest(starteDate,endDate);
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		/*try {
			String dateInString = "01/01/2021";
			Date starteDate = formatter.parse(dateInString);
			String dateInString2 = "01/02/2021";
			Date endDate = formatter.parse(dateInString2);
			staticEmailService.sendEtatWeekly(starteDate,endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/
		/*storageService2.deleteAll();
		storageService2.init();*/
		
		/*
		
		int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		
		System.out.println(thisMonth);
		System.out.println(thisYear);

		customerDao.save(new Customer("05959", "MUNISYS", false, null, null));
		customerDao.save(new Customer("05960", "ONCF", false, null, null));
		
		//new Project("prjID", "F5", "En cours", "05959", ,  "value systeme", 14);
		
		projectDao.save(new Project("prjID", "F5", "En cours", new Customer("05959", "MUNISYS", false, null, null), new Date(), "Mr Alouane", "Value Systeme", 10, 10*480, false));
		projectDao.save(new Project("prjID2", "F6", "En cours", new Customer("05959", "MUNISYS", false, null, null), new Date(), "Mr Khalid", "Réseaux", 15, 15*480, false));
		
		
		
		Direction direction = new Direction();
		direction.setName("Service Client");
		directionDao.save(direction);
		
		Service service = new Service();
		service.setDirection(direction);
		service.setServName("Système d'information");
		serviceDao.save(service);
		 
		 
		accountService.saveUser(new AppUser("admin","admin", "admin", "1234",service,"mehdi.jpg"));
		accountService.saveUser(new AppUser("user","user","user", "1234",service,"mehdi.jpg"));
		accountService.saveUser(new AppUser("Mehdi","Bouhafs","mbouhafs", "Bouhafsmehdi2",service,"mehdi.jpg"));
		accountService.saveUser(new AppUser("Stage","01","stage01", "123456",service,"stage01.jpg"));
		accountService.saveUser(new AppUser("Khalid","ROQAI-CHAOUI ","kchaoui", "123456",service,"mehdi.jpg"));
		
		accountService.saveProfile(new AppProfile(null, "Salarie_SI",null, null));
		accountService.saveAuthorisation("write_project_activity");
		accountService.saveAuthorisation("write_avant_vente_activity");
		accountService.saveAuthorisation("renouvellement");
		accountService.addAuthorisationToProfile("renouvellement", "Salarie_SI");
		accountService.addAuthorisationToProfile("write_project_activity", "Salarie_SI");
		accountService.addAuthorisationToProfile("write_avant_vente_activity", "Salarie_SI");
		accountService.addProfileToUser("stage01", "Salarie_SI");*/
		/*
		Request request = new Request("1111111111", "objDesc", "ObjIdentiVal", "ContratExcode", new Date(), new Customer("05959", "MUNISYS", false, null, null), "Nature de lincident", 1, null);
		
		requestRepository.save(request);*/
	}
	private static Date getDateBeforeDay(int day) {
		
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DATE, -day);
	    Date date = calendar.getTime();
	    return date;
	}




	public String getMyEnvironnement() {
		return myEnvironnement;
	}




	public void setMyEnvironnement(String myEnvironnement) {
		this.myEnvironnement = myEnvironnement;
	}
	
}
