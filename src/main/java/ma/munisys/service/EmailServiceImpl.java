package ma.munisys.service;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.transaction.Transactional;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.munisys.dao.ActivityRepository;
import ma.munisys.dao.ServiceDao;
import ma.munisys.dao.UserRepository;
import ma.munisys.entities.Activity;
import ma.munisys.entities.ActivityDevCompetence;
import ma.munisys.entities.ActivityHoliday;
import ma.munisys.entities.ActivityPM;
import ma.munisys.entities.ActivityProject;
import ma.munisys.entities.ActivityRequest;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.Produit;
import ma.munisys.model.DureeActiviteMois;

@Service
public class EmailServiceImpl implements EmailService {
	private final Path rootLocation = Paths.get("upload-dir");
	private final static String usernamemail = "noreply-mtime";
	private final static String password = "123456";
	@Autowired
	private ServiceDao service;
	@Autowired
	private ServiceDao userService;
	
	
	@Autowired
	private UserRepository userDao;
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	
	@Override
	public void notifyByActivity(Activity activity, String username) {
		
		String users = "";
		SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm");
		
     
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "pretoria");
        props.put("mail.smtp.port", "25");
       
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usernamemail, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply-mtime@munisys.net.ma"));
            InternetAddress[] myActionsList = InternetAddress.parse(username);
            message.setRecipients(Message.RecipientType.TO,myActionsList);
            
            message.setSubject("Nouvelle Activtée planifié "+formater.format(activity.getDteStrt())+" au "+ formater.format(activity.getDteEnd()));
            
            
            BodyPart messageBodyPart0 = new MimeBodyPart();
            

            // Fill the message
            messageBodyPart0.setText("<h4>Bonjour</h4> "+"\n" + 
            		"Merci de noter que Mr Chaoui Khalid vous a planifié une nouvelle activité sur MTime du "+formater.format(activity.getDteStrt())+" au "+ formater.format(activity.getDteEnd())+".\n\n" + 
            		"Détail de l'activitée ci-dessous :" +"\n\n" + 
            		"<strong>Nature :</strong> "+ activity.getNature()+"\n\n" + 
            		"Client : "+ activity.getCustomer().getName()+"\n\n" + 
            		"Projet : "+ ((ActivityProject) activity).getProject().getPrjName()+"\n\n" + 
            		"Action : "+ activity.getComments()+"\n\n" + 
            		"Vous pouvez changer le statut de cette activitée en cliquant sur le lien ci-après : https://marrakech:8443/MTime/#/activity/my-activities" + "\n\n"+
            		"Cordialement,\n\n"+
            		"MTime");
            
            String msg = "<h4>Bonjour</h4>" + 
            		"<p>Merci de noter que Mr Chaoui Khalid vous a planifié une nouvelle activité sur MTime du "+formater.format(activity.getDteStrt())+" au "+ formater.format(activity.getDteEnd())+".</p>" +
            		"<p>Détail de l'activitée ci-dessous :</p>" + 
            		"<p><strong>Nature :</strong> "+ activity.getNature()+"</p>" + 
            		"<p><strong>Client :</strong> "+ activity.getCustomer().getName()+"</p>" + 
            		"<p><strong>Projet :</strong> "+ ((ActivityProject) activity).getProject().getPrjName()+"</p>" + 
            		"<p><strong>Action :</strong> "+ activity.getComments()+"</p>" + 
            		"<p>Vous pouvez changer le statut de cette activitée en cliquant sur le lien ci-après : <strong>https://marrakech:8443/MTime/#/activity/my-activities</strong></p>" +
            		"<p><strong>Cordialement</strong></p>"+
            		"<p><strong>MTime</strong></p>";
           
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart0);
         
            message.setContent(msg, "text/html");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
	}
	@Override
	@Transactional
	public void sendEtatWeekly(Date startDate,Date endDate) {
		
		try {
		List<String> cc=new ArrayList<String>();
			
			List<AppUser> userByGroups  =userDao.searchUserByServices();
			Set<AppUser> usersBySet=new HashSet<AppUser>(userByGroups);
			for(AppUser user : usersBySet) {
				
				if(!user.isDisabled()) { 
					
					ma.munisys.entities.Service serviceUser =service.findOne(user.getService().getId());
					if(serviceUser!=null) {
							AppUser responsable = serviceUser.getResponsable();
							cc=new ArrayList<String>();
							cc.add("kchaoui@munisys.net.ma");
							if(responsable!=null)
							
								if(responsable.getUsername().equals(user.getUsername())) {
									//cc.add("mehdibouhafs17@gmail.com");
									//cc.add("abassou@munisys.net.ma");
									
								    //cc.add(responsable.getUsername()+"@munisys.net.ma");
									
									List<Activity> activitiesByService =activityRepository.findActivityBetweenDateSaisieGrouped(user.getService().getId(), lessHoursToDate(startDate),lessHoursToDate( endDate));
									
									Map<String,Set<Activity>> setActivities = new HashMap<String, Set<Activity>>();
									Map<String,Map<String,Double>> activitiesStatistics = new HashMap<String,Map<String,Double>>();
									
									for(Activity a : activitiesByService) {
										//Activity a = activityRepository.findOne(id);
										
										if(!setActivities.containsKey(a.getUser().getUsername())) {
											setActivities.put(a.getUser().getUsername(),new HashSet<Activity>());
										}
										
										setActivities.get(a.getUser().getUsername()).add(a);
										
										if(a instanceof ActivityHoliday==false) {
											
											if(!activitiesStatistics.containsKey(a.getUser().getUsername())) {
												activitiesStatistics.put(a.getUser().getLastName()+" "+a.getUser().getFirstName(),new HashMap<String, Double>());
											}
											if(!activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).containsKey(a.getTypeActivite())) {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), (a.getDurtion()/60)/8);
											}else {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), activitiesStatistics.get(a.getUser().getUsername()).get(a.getTypeActivite())+((a.getDurtion()/60)/8));
											}
										
										}else {
											if(!activitiesStatistics.containsKey(a.getUser().getUsername())) {
												activitiesStatistics.put(a.getUser().getLastName()+" "+a.getUser().getFirstName(),new HashMap<String, Double>());
											}
											if(!activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).containsKey(a.getTypeActivite())) {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), ((a.getDurtion()/60)/24)*8);
											}else {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), activitiesStatistics.get(a.getUser().getUsername()).get(a.getTypeActivite())+(((a.getDurtion()/60)/24)*8));
											}
										}
										
										
										
									}
									
									String fileName = getExcelFileForResponsable(setActivities,startDate,activitiesStatistics);
									
								
									sendMailResponsable(user.getUsername()+"@munisys.net.ma", cc, startDate,endDate, fileName,serviceUser.getServName());
								
								
									continue;
								}
					}
					
					List<Long> activities =activityService.findActivityDateSaisieBetween(user.getUsername(), startDate, endDate);
					List<DureeActiviteMois> activitiesOfYear=activityRepository.findActivityByUserByYear2(user.getUsername(), new Date());
					
					
					
					Map<String,Map<Integer,Double>> setActivitiesByTypeActiviteDurationByMonth = new HashMap<String, Map<Integer,Double>>();
					List<Activity> setActivities = new ArrayList<Activity>();
					Map<String,Double> activitiesStatistics = new HashMap<String,Double>();
					
					for(Long id : activities) {
						Activity a = activityRepository.findOne(id);
						if(a instanceof ActivityHoliday==false ) {
							if(!activitiesStatistics.containsKey(a.getTypeActivite())) {
								activitiesStatistics.put(a.getTypeActivite(), (a.getDurtion()/60)/8);
							}else {
								activitiesStatistics.put(a.getTypeActivite(), activitiesStatistics.get(a.getTypeActivite())+ ((a.getDurtion()/60)/8));
							}
						}else {
							if(!activitiesStatistics.containsKey(a.getTypeActivite())) {
								activitiesStatistics.put(a.getTypeActivite(), ((a.getDurtion()/60)/24));
							}else {
								activitiesStatistics.put(a.getTypeActivite(), activitiesStatistics.get(a.getTypeActivite())+ ((a.getDurtion()/60)/24));
							}
						}
						
						
						setActivities.add(a);
						
					}
					
					
					for(DureeActiviteMois dureeActiviteMois:activitiesOfYear) {
					
						if(!setActivitiesByTypeActiviteDurationByMonth.containsKey(dureeActiviteMois.getTypeActivite()))
						{
							
							setActivitiesByTypeActiviteDurationByMonth.put(dureeActiviteMois.getTypeActivite(), new HashMap<Integer, Double>());
						}
						if(!dureeActiviteMois.getTypeActivite().equals("Activité congé")) {
							setActivitiesByTypeActiviteDurationByMonth.get(dureeActiviteMois.getTypeActivite()).put(dureeActiviteMois.getMonth(), ((dureeActiviteMois.getDuration()/60)/8));
						}else {
							setActivitiesByTypeActiviteDurationByMonth.get(dureeActiviteMois.getTypeActivite()).put(dureeActiviteMois.getMonth(), ((dureeActiviteMois.getDuration()/60)/24));
							
						}
						
						}
					
					
					String nom = user.getFirstName()!=null?user.getFirstName():"";
					String prenom = user.getLastName()!=null?user.getLastName():"";
					
					String fileName = getExcelFile(user.getUsername(),setActivities,startDate,activitiesStatistics,setActivitiesByTypeActiviteDurationByMonth);
					
				
					sendMail(nom+" "+prenom,user.getUsername()+"@munisys.net.ma", cc, startDate,endDate, fileName);
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public void sendEtatWeeklyTest(Date startDate,Date endDate) {
		try {
		
		List<String> cc=new ArrayList<String>();

			List<AppUser> userByGroups  =userDao.searchUserByServices();
			Set<AppUser> usersBySet=new HashSet<AppUser>(userByGroups);
			
			for(AppUser user : usersBySet) {
				
				if(!user.isDisabled()) { 
					ma.munisys.entities.Service serviceUser =service.findOne(user.getService().getId());
					if(serviceUser!=null) {
							AppUser responsable = serviceUser.getResponsable();
							cc=new ArrayList<String>();
							cc.add("kchaoui-teste@munisys.net.ma");
							cc.add("mehdibouhafs17@gmail.com");
							//cc.add("abassou@munisys.net.ma");
							if(responsable!=null)
								if(responsable.getUsername().equals(user.getUsername())) {
									
									List<Activity> activitiesByService =activityRepository.findActivityBetweenDateSaisieGrouped(user.getService().getId(), lessHoursToDate(startDate),lessHoursToDate( endDate));
									
									Map<String,Set<Activity>> setActivities = new HashMap<String, Set<Activity>>();
									Map<String,Map<String,Double>> activitiesStatistics = new HashMap<String,Map<String,Double>>();
									
									for(Activity a : activitiesByService) {
										//Activity a = activityRepository.findOne(id);
										
										if(!setActivities.containsKey(a.getUser().getUsername())) {
											setActivities.put(a.getUser().getUsername(),new HashSet<Activity>());
										}
										
										setActivities.get(a.getUser().getUsername()).add(a);
										if(a instanceof ActivityHoliday==false) {
											
											if(!activitiesStatistics.containsKey(a.getUser().getUsername())) {
												activitiesStatistics.put(a.getUser().getLastName()+" "+a.getUser().getFirstName(),new HashMap<String, Double>());
											}
											if(!activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).containsKey(a.getTypeActivite())) {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), (a.getDurtion()/60)/8);
											}else {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), activitiesStatistics.get(a.getUser().getUsername()).get(a.getTypeActivite())+((a.getDurtion()/60)/8));
											}
										
										}else {
											if(!activitiesStatistics.containsKey(a.getUser().getUsername())) {
												activitiesStatistics.put(a.getUser().getLastName()+" "+a.getUser().getFirstName(),new HashMap<String, Double>());
											}
											if(!activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).containsKey(a.getTypeActivite())) {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), ((a.getDurtion()/60)/24));
											}else {
												activitiesStatistics.get(a.getUser().getLastName()+" "+a.getUser().getFirstName()).put(a.getTypeActivite(), activitiesStatistics.get(a.getUser().getUsername()).get(a.getTypeActivite())+(((a.getDurtion()/60)/24)));
											}
										}
										
										
									}
									
									String fileName = getExcelFileForResponsable(setActivities,startDate,activitiesStatistics);
									
								
									sendMailResponsable(user.getUsername()+"-teste@munisys.net.ma", cc, startDate,endDate, fileName,serviceUser.getServName());
								
								
									continue;
									}
					}
					
					
					List<Long> activities =activityService.findActivityDateSaisieBetween(user.getUsername(), startDate, endDate);
					List<DureeActiviteMois> activitiesOfYear=activityRepository.findActivityByUserByYear2(user.getUsername(), new Date());
					
					Map<String,Map<Integer,Double>> setActivitiesByTypeActiviteDurationByMonth = new HashMap<String, Map<Integer,Double>>();
					List<Activity> setActivities = new ArrayList<Activity>();
					Map<String,Double> activitiesStatistics = new HashMap<String,Double>();
					
					for(Long id : activities) {
						Activity a = activityRepository.findOne(id);
						if(a instanceof ActivityHoliday==false ) {
							if(!activitiesStatistics.containsKey(a.getTypeActivite())) {
								activitiesStatistics.put(a.getTypeActivite(), (a.getDurtion()/60)/8);
							}else {
								activitiesStatistics.put(a.getTypeActivite(), activitiesStatistics.get(a.getTypeActivite())+ ((a.getDurtion()/60)/8));
							}
						}else {
							if(!activitiesStatistics.containsKey(a.getTypeActivite())) {
								activitiesStatistics.put(a.getTypeActivite(), ((a.getDurtion()/60)/24));
							}else {
								activitiesStatistics.put(a.getTypeActivite(), activitiesStatistics.get(a.getTypeActivite())+ (((a.getDurtion()/60)/24)));
							}
						}
						
						
						setActivities.add(a);
						
					}
					
					
					for(DureeActiviteMois dureeActiviteMois:activitiesOfYear) {
					
						if(!setActivitiesByTypeActiviteDurationByMonth.containsKey(dureeActiviteMois.getTypeActivite()))
						{
							
							setActivitiesByTypeActiviteDurationByMonth.put(dureeActiviteMois.getTypeActivite(), new HashMap<Integer, Double>());
						}
						if(!dureeActiviteMois.getTypeActivite().equals("Activité congé")) {
							setActivitiesByTypeActiviteDurationByMonth.get(dureeActiviteMois.getTypeActivite()).put(dureeActiviteMois.getMonth(), ((dureeActiviteMois.getDuration()/60)/8));
						}else {
							setActivitiesByTypeActiviteDurationByMonth.get(dureeActiviteMois.getTypeActivite()).put(dureeActiviteMois.getMonth(), ((dureeActiviteMois.getDuration()/60)/24));
						
						}
					}
					
					
					String nom = user.getFirstName()!=null?user.getFirstName():"";
					String prenom = user.getLastName()!=null?user.getLastName():"";
					
					String fileName = getExcelFile(user.getUsername(),setActivities,startDate,activitiesStatistics,setActivitiesByTypeActiviteDurationByMonth);
					
				
					sendMailTeste(nom+" "+prenom,user.getUsername()+"-teste@munisys.net.ma", cc, startDate,endDate, fileName);
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getExcelFile(String username, List<Activity> activities,Date date,Map<String,Double> activitiesStatistics,Map<String,Map<Integer,Double>> setActivitiesByTypeActiviteDurationByMonth) {
		 SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		 String filename=null;
		try {
			 filename = rootLocation.toString()+"/"+username+"_"+new SimpleDateFormat("dd_MM_yyyy").format(date)+".xls" ;
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
	            
	            String month = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
	            
	            HSSFSheet sheet = workbook.createSheet("Rapport activité_Semaine"+week+"_"+month);  
	           
	            HSSFSheet sheet2 = workbook.createSheet("Analyse activité_Semaine"+week+"_"+month);  
	            
	            HSSFSheet sheet3 = workbook.createSheet("Activité_"+Calendar.getInstance().get(Calendar.YEAR));  
	            
	            HSSFSheet sheet4 = workbook.createSheet("Détail_activité_"+Calendar.getInstance().get(Calendar.YEAR));  
		           
	            HSSFRow rowhead3 = sheet3.createRow((short)0);
	            
	            HSSFCell cell1Sheet3=rowhead3.createCell(0);
	            cell1Sheet3.setCellValue("Type d'activité / Mois(Durée en JH)");
	            HSSFCell cell2Sheet3=rowhead3.createCell(1);
	            cell2Sheet3.setCellValue("Janvier");
	            HSSFCell cell3Sheet3=rowhead3.createCell(2);
	            cell3Sheet3.setCellValue("Février");
	            HSSFCell cell4Sheet3=rowhead3.createCell(3);
	            cell4Sheet3.setCellValue("Mars");
	            HSSFCell cell5Sheet3=rowhead3.createCell(4);
	            cell5Sheet3.setCellValue("Avril");
	            HSSFCell cell6Sheet3=rowhead3.createCell(5);
	            cell6Sheet3.setCellValue("Mai");
	            HSSFCell cell7Sheet3=rowhead3.createCell(6);
	            cell7Sheet3.setCellValue("Juin");
	            HSSFCell cell8Sheet3=rowhead3.createCell(7);
	            cell8Sheet3.setCellValue("Juillet");
	            HSSFCell cell9Sheet3=rowhead3.createCell(8);
	            cell9Sheet3.setCellValue("Aout");
	            HSSFCell cell10Sheet3=rowhead3.createCell(9);
	            cell10Sheet3.setCellValue("Septembre");
	            HSSFCell cell10Sheet4=rowhead3.createCell(10);
	            cell10Sheet4.setCellValue("Octobre");
	            HSSFCell cell10Sheet5=rowhead3.createCell(11);
	            cell10Sheet5.setCellValue("Novembre");
	            HSSFCell cell10Sheet6=rowhead3.createCell(12);
	            cell10Sheet6.setCellValue("Décembre");
	            
	           
	            
	        
	            HSSFRow rowhead2 = sheet2.createRow((short)0);
		    
	            
	            HSSFCell cellsheet21=  rowhead2.createCell(0);
	            cellsheet21.setCellValue("Type d'activitée");
	            
	            HSSFCell cellsheet22=  rowhead2.createCell(1);
	            cellsheet22.setCellValue("Durée JH");
	            
	            
	            HSSFRow rowhead = sheet.createRow((short)0);
	            HSSFCell cell0 =rowhead.createCell(0);
	            cell0.setCellValue("Date de saisie");
	            HSSFCell cell1=  rowhead.createCell(1);
	            cell1.setCellValue("Date d'intervention");
	            HSSFCell cell2=rowhead.createCell(2);
	            cell2.setCellValue("Type d'activitée");
	            HSSFCell cell3=rowhead.createCell(3);
	            cell3.setCellValue("Client");
	            HSSFCell cell4=rowhead.createCell(4);
	            cell4.setCellValue("Projet || N°demande");
	            HSSFCell cell5=rowhead.createCell(5);
	            cell5.setCellValue("Durée");
	            HSSFCell cell6=rowhead.createCell(6);
	            cell6.setCellValue("Rôle");
	            HSSFCell cell7=rowhead.createCell(7);
	            cell7.setCellValue("Lieu");
	            HSSFCell cell8=rowhead.createCell(8);
	            cell8.setCellValue("Action");
	            HSSFCell cell9=rowhead.createCell(9);
	            cell9.setCellValue("Produits");
	            HSSFCell cell10=rowhead.createCell(10);
	            cell10.setCellValue("Commentaire");
	            
	           
	            
	            CellStyle style=null;
	            // Creating a font
	               HSSFFont font= workbook.createFont();
	               font.setFontHeightInPoints((short)11);
	               font.setFontName("Arial");
	               font.setColor(IndexedColors.BLUE.getIndex());
	               font.setBold(true);
	               font.setItalic(true);
	        
	               style=workbook.createCellStyle();;
	               
	               // Setting font to style
	               style.setFont(font);
	        
	               // Setting cell style
	               cell0.setCellStyle(style);cell1.setCellStyle(style);cell2.setCellStyle(style);
	               cell3.setCellStyle(style);cell4.setCellStyle(style);cell5.setCellStyle(style);
	               cell6.setCellStyle(style);cell7.setCellStyle(style);cell8.setCellStyle(style);
	               cell9.setCellStyle(style);cell10.setCellStyle(style);
	               cellsheet21.setCellStyle(style);cellsheet22.setCellStyle(style);
	               
	               cell1Sheet3.setCellStyle(style);cell2Sheet3.setCellStyle(style);
	               cell3Sheet3.setCellStyle(style);cell4Sheet3.setCellStyle(style);cell5Sheet3.setCellStyle(style);
	               cell6Sheet3.setCellStyle(style);cell7Sheet3.setCellStyle(style);cell8Sheet3.setCellStyle(style);
	               cell9Sheet3.setCellStyle(style);cell10Sheet3.setCellStyle(style);
	               cell10Sheet6.setCellStyle(style);cell10Sheet4.setCellStyle(style);cell10Sheet5.setCellStyle(style);
	           
	               
	               
	                rowhead = sheet4.createRow((short)0);
		            HSSFCell cell0Sheet2 =rowhead.createCell(0);
		            cell0Sheet2.setCellValue("Date de saisie");
		            HSSFCell cell1Sheet2=  rowhead.createCell(1);
		            cell1Sheet2.setCellValue("Date d'intervention");
		            HSSFCell cell2Sheet2=rowhead.createCell(2);
		            cell2Sheet2.setCellValue("Type d'activitée");
		            HSSFCell cell3Sheet2=rowhead.createCell(3);
		            cell3Sheet2.setCellValue("Client");
		            HSSFCell cell4Sheet2=rowhead.createCell(4);
		            cell4Sheet2.setCellValue("Projet || N°demande");
		            HSSFCell cell5Sheet2=rowhead.createCell(5);
		            cell5Sheet2.setCellValue("Durée");
		            HSSFCell cell6Sheet2=rowhead.createCell(6);
		            cell6Sheet2.setCellValue("Rôle");
		            HSSFCell cell7Sheet2=rowhead.createCell(7);
		            cell7Sheet2.setCellValue("Lieu");
		            HSSFCell cell8Sheet2=rowhead.createCell(8);
		            cell8Sheet2.setCellValue("Action");
		            HSSFCell cell9Sheet2=rowhead.createCell(9);
		            cell9Sheet2.setCellValue("Produits");
		            HSSFCell cell10Sheet2=rowhead.createCell(10);
		            cell10Sheet2.setCellValue("Commentaire");
	               
		            
		            cell0Sheet2.setCellStyle(style);cell1Sheet2.setCellStyle(style);cell2Sheet2.setCellStyle(style);
		            cell3Sheet2.setCellStyle(style);cell4Sheet2.setCellStyle(style);cell5Sheet2.setCellStyle(style);
		            cell6Sheet2.setCellStyle(style);cell7Sheet2.setCellStyle(style);cell8Sheet2.setCellStyle(style);  cell9Sheet2.setCellStyle(style);  cell10Sheet2.setCellStyle(style);
		               
	               
	               
	            int i=1;
            for(Activity activity : activities) {
	            HSSFRow row = sheet.createRow((short)i++);
	            Date dateSaisie= activity.getCreatedAt();
	            
	            String duration ="";
	            row.createCell(0).setCellValue(formater.format(addHoursToDate(dateSaisie)));
	            if(activity instanceof ActivityHoliday == false ) {
	            	row.createCell(1).setCellValue(formater.format(addHoursToDate(activity.getDteStrt())));
	            	 int hours = (int) (activity.getDurtion() / 60);
	 	            int minutes = (int) (activity.getDurtion() % 60);
	 	             duration =  String.format("%d", hours) + ":"+String.format("%02d", minutes); 
	 	           
	            }else {
	            
	            	row.createCell(1).setCellValue(formater.format(addHoursToDate(activity.getDteStrt()))+ " Au "+formater.format(addHoursToDate(activity.getDteEnd())));
	            	 	
	            		int hours = (int) ((activity.getDurtion()/24) / 60);
		 	            //int minutes = (int) ((activity.getDurtion()/24) % 60); 
		 	            duration =   (hours*8) + ":00";
			 	           
		 	            
	            }
	            row.createCell(2).setCellValue(activity.getTypeActivite());
	            
	            if(activity.getCustomer()!=null)
	            	row.createCell(3).setCellValue(activity.getCustomer().getName());
	            if(activity instanceof ActivityProject) {
	            	 if(((ActivityProject)activity).getProject()!=null){
	            	 row.createCell(4).setCellValue(((ActivityProject)activity).getProject().getPrjName());
	            	 
	            	 
	            	 }
	            }
	            	 if(activity instanceof ActivityRequest) {
		            	 if(((ActivityRequest)activity).getRequest()!=null){
		            	 row.createCell(4).setCellValue(((ActivityRequest)activity).getRequest().getRqtExcde());
		            	 
		            	 }
		            	 row.createCell(6).setCellValue((activity.isPrincipal()?"Principale":"Secondaire"));
			 	           
	            	 }
	            	 if(activity instanceof ActivityPM) {
		            	 if(((ActivityPM)activity).getRequest()!=null){
		            	 row.createCell(4).setCellValue(((ActivityPM)activity).getRequest().getRqtExcde());
		            }
		            	 row.createCell(6).setCellValue((activity.isPrincipal()?"Principale":"Secondaire"));
			 	           
	            	 }
	            
	           
	            row.createCell(5).setCellValue(duration);
	            row.createCell(7).setCellValue(activity.getLieu());
	            row.createCell(8).setCellValue(activity.getNature());
	            row.createCell(9).setCellValue(getProduits(activity));
	            row.createCell(10).setCellValue(activity.getComments());
	            sheet.autoSizeColumn(0);
	            sheet.autoSizeColumn(1);
	            sheet.autoSizeColumn(2);
	            sheet.autoSizeColumn(3);
	            sheet.autoSizeColumn(4);
	            sheet.autoSizeColumn(5);
	            sheet.autoSizeColumn(6);
	            sheet.autoSizeColumn(7);
	            sheet.autoSizeColumn(8);
	            sheet.autoSizeColumn(9);
	            sheet.autoSizeColumn(10);

            }
            int j=1;
            for(Map.Entry<String,Double> stat : activitiesStatistics.entrySet()) {
	            HSSFRow row = sheet2.createRow((short)j++);
	            row.createCell(0).setCellValue(stat.getKey());
	            row.createCell(1).setCellValue(new DecimalFormat("##.##").format(stat.getValue()));
	            sheet2.autoSizeColumn(0);
	            sheet2.autoSizeColumn(1);
            }
            
            int y=1;
            for(Map.Entry<String, Map<Integer,Double>> entry : setActivitiesByTypeActiviteDurationByMonth.entrySet()) {
	            HSSFRow row = sheet3.createRow((short)y++);
	        
	            row.createCell(0).setCellValue(entry.getKey());
	            
	            if(entry.getValue().containsKey(1)){
	            	 row.createCell(1).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(1)));
	            }
	            if(entry.getValue().containsKey(2)){
	            	 row.createCell(2).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(2)));
	            }
	            if(entry.getValue().containsKey(3)){
	            	 row.createCell(3).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(3)));
	            }
	            if(entry.getValue().containsKey(4)){
	            	 row.createCell(4).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(4)));
	            }
	            if(entry.getValue().containsKey(5)){
	            	 row.createCell(5).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(5)));
	            }
	            
	            if(entry.getValue().containsKey(6)){
	            	 row.createCell(6).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(6)));
	            }
	            if(entry.getValue().containsKey(7)){
	            	 row.createCell(7).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(7)));
	            }
	            
	            if(entry.getValue().containsKey(8)){
	            	 row.createCell(8).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(8)));
	            }
	            
	            if(entry.getValue().containsKey(9)){
	            	 row.createCell(9).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(9)));
	            }
	            
	            if(entry.getValue().containsKey(10)){
	            	 row.createCell(10).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(10)));
	            }
	            
	            if(entry.getValue().containsKey(11)){
	            	 row.createCell(11).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(11)));
	            }
	            
	            if(entry.getValue().containsKey(12)){
	            	 row.createCell(12).setCellValue(new DecimalFormat("##.##").format(entry.getValue().get(12)));
	            }
	             
	             
	            sheet3.autoSizeColumn(0);
	            sheet3.autoSizeColumn(1);
	            sheet3.autoSizeColumn(2);
	            sheet3.autoSizeColumn(3);
	            sheet3.autoSizeColumn(4);
	            sheet3.autoSizeColumn(5);
	            sheet3.autoSizeColumn(6);
	            sheet3.autoSizeColumn(7);
	            sheet3.autoSizeColumn(8);
	            sheet3.autoSizeColumn(9);
	            sheet3.autoSizeColumn(10);
	            sheet3.autoSizeColumn(11);
	            sheet3.autoSizeColumn(12);
	     

            }
            
            int t=1;
            for(Long idActivity : activityRepository.findActivityByUserByYear(username, new Date())) {
	            Activity activity = activityRepository.findActivity(idActivity);
            	HSSFRow row = sheet4.createRow((short)t++);
	            Date dateSaisie= activity.getCreatedAt();
	            
	            String duration ="";
	            row.createCell(0).setCellValue(formater.format(addHoursToDate(dateSaisie)));
	            if(activity instanceof ActivityHoliday == false ) {
	            	row.createCell(1).setCellValue(formater.format(addHoursToDate(activity.getDteStrt())));
	            	 int hours = (int) (activity.getDurtion() / 60);
	 	            int minutes = (int) (activity.getDurtion() % 60);
	 	             duration =  String.format("%d", hours) + ":"+String.format("%02d", minutes); 
	 	           
	            }else {
	            
	            	row.createCell(1).setCellValue(formater.format(addHoursToDate(activity.getDteStrt()))+ " Au "+formater.format(addHoursToDate(activity.getDteEnd())));
	            	 	
	            		int hours = (int) ((activity.getDurtion()/24) / 60);
		 	            //int minutes = (int) ((activity.getDurtion()/24) % 60); 
		 	            duration =   (hours*8) + ":00";
			 	           
		 	            
	            }
	            row.createCell(2).setCellValue(activity.getTypeActivite());
	            
	            if(activity.getCustomer()!=null)
	            	row.createCell(3).setCellValue(activity.getCustomer().getName());
	            if(activity instanceof ActivityProject) {
	            	 if(((ActivityProject)activity).getProject()!=null){
	            	 row.createCell(4).setCellValue(((ActivityProject)activity).getProject().getPrjName());
	            	 
	            	 
	            	 }
	            }
	            	 if(activity instanceof ActivityRequest) {
		            	 if(((ActivityRequest)activity).getRequest()!=null){
		            	 row.createCell(4).setCellValue(((ActivityRequest)activity).getRequest().getRqtExcde());
		            	 
		            	 }
		            	 row.createCell(6).setCellValue((activity.isPrincipal()?"Principale":"Secondaire"));
			 	           
	            	 }
	            	 if(activity instanceof ActivityPM) {
		            	 if(((ActivityPM)activity).getRequest()!=null){
		            	 row.createCell(4).setCellValue(((ActivityPM)activity).getRequest().getRqtExcde());
		            }
		            	 row.createCell(6).setCellValue((activity.isPrincipal()?"Principale":"Secondaire"));
			 	           
	            	 }
	            
	           
	            row.createCell(5).setCellValue(duration);
	            row.createCell(7).setCellValue(activity.getLieu());
	            row.createCell(8).setCellValue(activity.getNature());
	            row.createCell(9).setCellValue(getProduits(activity));
	            row.createCell(10).setCellValue(activity.getComments());
	            sheet4.autoSizeColumn(0);
	            sheet4.autoSizeColumn(1);
	            sheet4.autoSizeColumn(2);
	            sheet4.autoSizeColumn(3);
	            sheet4.autoSizeColumn(4);
	            sheet4.autoSizeColumn(5);
	            sheet4.autoSizeColumn(6);
	            sheet4.autoSizeColumn(7);
	            sheet4.autoSizeColumn(8);
	            sheet4.autoSizeColumn(9);
	            sheet4.autoSizeColumn(10);

            }

            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    
		return filename;
	}
	
	private String getExcelFileForResponsable(Map<String,Set<Activity>> setActivities,Date date,Map<String,Map<String,Double>> activitiesStatistics) {
		 SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		 String filename=null;
		try {
			 filename = rootLocation.toString()+"/TeamWeeklyReport_"+new SimpleDateFormat("dd_MM_yyyy").format(date)+".xls" ;
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
	            
	            String month = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
	            
	            HSSFSheet sheet = workbook.createSheet("Rapport activité_Semaine"+week+"_"+month);  
	           
	            HSSFSheet sheet2 = workbook.createSheet("Analyse activité_Semaine"+week+"_"+month);  
	            
	            HSSFRow rowhead2 = sheet2.createRow((short)0);
		           
	            HSSFCell cellsheet20=  rowhead2.createCell(0);
	            cellsheet20.setCellValue("Collaborateur");
	            
	            HSSFCell cellsheet21=  rowhead2.createCell(1);
	            cellsheet21.setCellValue("Type d'activitée");
	            
	            HSSFCell cellsheet22=  rowhead2.createCell(2);
	            cellsheet22.setCellValue("Durée");
	            
	            
	            HSSFRow rowhead = sheet.createRow((short)0);
	            HSSFCell cell0=  rowhead.createCell(0);
	            cell0.setCellValue("Collaborateur");
	            HSSFCell cell1=  rowhead.createCell(1);
	            cell1.setCellValue("Date de saisie");
	            HSSFCell cell20=rowhead.createCell(2);
	            cell20.setCellValue("Date d'intervention");
	            HSSFCell cell2=rowhead.createCell(3);
	            cell2.setCellValue("Type d'activitée");
	            HSSFCell cell3=rowhead.createCell(4);
	            cell3.setCellValue("Client");
	            HSSFCell cell4=rowhead.createCell(5);
	            cell4.setCellValue("Projet || N°demande");
	            HSSFCell cell5=rowhead.createCell(6);
	            cell5.setCellValue("Durée");
	            HSSFCell cell6=rowhead.createCell(7);
	            cell6.setCellValue("Rôle");
	            HSSFCell cell7=rowhead.createCell(8);
	            cell7.setCellValue("Lieu");
	            HSSFCell cell8=rowhead.createCell(9);
	            cell8.setCellValue("Action");
	            HSSFCell cell9=rowhead.createCell(10);
	            cell9.setCellValue("Produits");
	            HSSFCell cell10=rowhead.createCell(11);
	            cell10.setCellValue("Commentaire");
	            
	            CellStyle style=null;
	            // Creating a font
	               HSSFFont font= workbook.createFont();
	               font.setFontHeightInPoints((short)10);
	               font.setFontName("Arial");
	               font.setColor(IndexedColors.BLUE.getIndex());
	               font.setBold(true);
	               font.setItalic(true);
	        
	               style=workbook.createCellStyle();;
	               
	               // Setting font to style
	               style.setFont(font);
	        
	               // Setting cell style
	               cell0.setCellStyle(style); cell1.setCellStyle(style);cell2.setCellStyle(style);
	               cell3.setCellStyle(style);cell4.setCellStyle(style);cell5.setCellStyle(style);
	               cell6.setCellStyle(style);cell7.setCellStyle(style);cell8.setCellStyle(style);
	               cell9.setCellStyle(style);cell10.setCellStyle(style);
	               cellsheet20.setCellStyle(style);cell20.setCellStyle(style);
	               cellsheet21.setCellStyle(style);
	               cellsheet22.setCellStyle(style);
	            int i=1;
	            String duration="";
           for( Map.Entry<String,Set<Activity>> activityMap : setActivities.entrySet()) {
        	   for(Activity activity : activityMap.getValue()) {
		            HSSFRow row = sheet.createRow((short)i++);
		            row.createCell(0).setCellValue(activity.getUser().getLastName()+" "+ activity.getUser().getFirstName());
			           
		            row.createCell(1).setCellValue(formater.format(addHoursToDate(activity.getCreatedAt())));
	            	 
		            if(activity instanceof ActivityHoliday == false ) {
		            	row.createCell(2).setCellValue(formater.format(addHoursToDate(activity.getDteStrt())));
		            	 int hours = (int) (activity.getDurtion() / 60);
				            int minutes = (int) (activity.getDurtion() % 60);
				             duration =  String.format("%d", hours) + ":"+String.format("%02d", minutes); 
				            
		            }else {
		            	row.createCell(2).setCellValue(formater.format(addHoursToDate(activity.getDteStrt()))+ " Au "+formater.format(addHoursToDate(activity.getDteEnd())));
		            	int hours = (int) (((activity.getDurtion()/24) / 60));
		 	            //int minutes = (int) ((activity.getDurtion()/24) % 60);
		 	             duration =(hours*8) + ":00";
		            }
		            
		            row.createCell(3).setCellValue(activity.getTypeActivite());
		            
		            if(activity.getCustomer()!=null)
		            	row.createCell(4).setCellValue(activity.getCustomer().getName());
		            if(activity instanceof ActivityProject) {
		            	 if(((ActivityProject)activity).getProject()!=null){
		            	 row.createCell(5).setCellValue(((ActivityProject)activity).getProject().getPrjName());
		            	 
		            	 
		            	 }
		            }
		            	 if(activity instanceof ActivityRequest) {
			            	 if(((ActivityRequest)activity).getRequest()!=null){
			            	 row.createCell(5).setCellValue(((ActivityRequest)activity).getRequest().getRqtExcde());
			            }
			            	 row.createCell(7).setCellValue((activity.isPrincipal()?"Principale":"Secondaire"));
					           
		            	 }
		            	 if(activity instanceof ActivityPM) {
			            	 if(((ActivityPM)activity).getRequest()!=null){
			            	 row.createCell(5).setCellValue(((ActivityPM)activity).getRequest().getRqtExcde());
			            
			            	 }
			            	 row.createCell(7).setCellValue((activity.isPrincipal()?"Principale":"Secondaire"));
					           
		            	 }
		            
		            	 
		           
		            row.createCell(6).setCellValue(duration);
		            row.createCell(8).setCellValue(activity.getLieu());
		            row.createCell(9).setCellValue(activity.getNature());
		            row.createCell(10).setCellValue(getProduits(activity));
		            row.createCell(11).setCellValue(activity.getComments());
		            sheet.autoSizeColumn(0);
		            sheet.autoSizeColumn(1);
		            sheet.autoSizeColumn(2);
		            sheet.autoSizeColumn(3);
		            sheet.autoSizeColumn(4);
		            sheet.autoSizeColumn(5);
		            sheet.autoSizeColumn(6);
		            sheet.autoSizeColumn(7);
		            sheet.autoSizeColumn(8);
		            sheet.autoSizeColumn(9);
		            sheet.autoSizeColumn(10);
		            sheet.autoSizeColumn(11);
		           
	           }
           }
           
           int j=1;
           for(Map.Entry<String,Map<String,Double>> stat : activitiesStatistics.entrySet()) {
	            
	            for(Map.Entry<String,Double> stat2 : stat.getValue().entrySet()) {
	            	HSSFRow row = sheet2.createRow((short)j++);
	            	row.createCell(0).setCellValue(stat.getKey());
		            row.createCell(1).setCellValue(stat2.getKey());
		            row.createCell(2).setCellValue(new DecimalFormat("##.##").format(stat2.getValue()));
		            sheet2.autoSizeColumn(0);
		            sheet2.autoSizeColumn(1);
		            sheet2.autoSizeColumn(2);
	            }
	           
	            sheet2.autoSizeColumn(0);
	            sheet2.autoSizeColumn(1);
	            sheet2.autoSizeColumn(2);
           }
           
           FileOutputStream fileOut = new FileOutputStream(filename);
           workbook.write(fileOut);
           fileOut.close();
           workbook.close();
           

       } catch ( Exception ex ) {
           ex.printStackTrace();
       }
   
		return filename;
	}
	private void sendMail(String nomPrenom,String receiver,List<String> cc,Date startDate,Date endDate,String fileName) {
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateFrom = startDate;
     
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "pretoria");
        props.put("mail.smtp.port", "25");
       
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usernamemail, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply-mtime@munisys.net.ma"));
            InternetAddress[] myActionsList = InternetAddress.parse(receiver);
            message.setRecipients(Message.RecipientType.TO,myActionsList);
            
            InternetAddress[] myCcList = new  InternetAddress[cc.size()];
            
         // To get the array of ccaddresses
            for( int i = 0; i < cc.size(); i++ ) {
            	myCcList[i] = new InternetAddress(cc.get(i));
                message.addRecipient(Message.RecipientType.CC, myCcList[i]);
            }
            
            
            
           InternetAddress[] myCCILIST = InternetAddress.parse("abassou@munisys.net.ma");
            message.setRecipients(Message.RecipientType.BCC,myCCILIST);
            
            
            Calendar calendar =Calendar.getInstance();
            
            int week = calendar.get(Calendar.WEEK_OF_MONTH);
            
           // String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
        
            message.setSubject("Rapport d'activité M-Time de "+nomPrenom+" de la semaine N°" +week );
            
            BodyPart messageBodyPart0 = new MimeBodyPart();
                        
            String msg = "<h4>Bonjour "+nomPrenom+",</h4>" + 
            		"<p>Je vous prie de trouver ci-joint le rapport d&acute;activité M-TIME de la semaine du "+formater.format(dateFrom)+" Au "+formater.format(endDate) +"</p>" +
            		"<p><strong>Cordialement</strong></p>"+
            		"<p><strong>M-TIME</strong></p>";
            Multipart multipart = new MimeMultipart();
            messageBodyPart0.setContent(msg, "text/html");
            multipart.addBodyPart(messageBodyPart0);
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart1.setDataHandler(new DataHandler(source));
            messageBodyPart1.setFileName(fileName.replace(rootLocation.toString(), "").replace("/", ""));
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("send mail to "+ nomPrenom);
            

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }finally {
			File f = new File(fileName);
			f.delete();
		}
	}
	
private void sendMailResponsable(String receiver,List<String> cc,Date startDate,Date endDate,String fileName,String serviceName) {
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateFrom = startDate;
     
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "pretoria");
        props.put("mail.smtp.port", "25");
       
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usernamemail, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply-mtime@munisys.net.ma"));
            InternetAddress[] myActionsList = InternetAddress.parse(receiver);
            message.setRecipients(Message.RecipientType.TO,myActionsList);
            
            InternetAddress[] myCcList = new  InternetAddress[cc.size()];
            
         // To get the array of ccaddresses
            for( int i = 0; i < cc.size(); i++ ) {
            	myCcList[i] = new InternetAddress(cc.get(i));
                message.addRecipient(Message.RecipientType.CC, myCcList[i]);
            }
            
            
            
           //InternetAddress[] myCCILIST = InternetAddress.parse("abassou@munisys.net.ma");
            //message.setRecipients(Message.RecipientType.BCC,myCCILIST);
            
            
            Calendar calendar =Calendar.getInstance();
            
            int week = calendar.get(Calendar.WEEK_OF_MONTH);
            
           // String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
        
            message.setSubject("Rapport d'activité M-Time de l'équipe "+serviceName+" de la semaine N°" +week );
            
            BodyPart messageBodyPart0 = new MimeBodyPart();
                        
            String msg = "<h4>Bonjour,</h4>" + 
            		"<p>Je vous prie de trouver ci-joint le rapport d&acute;activité M-TIME de la semaine du "+formater.format(dateFrom)+" Au "+formater.format(endDate) +"</p>" +
            		"<p><strong>Cordialement</strong></p>"+
            		"<p><strong>M-TIME</strong></p>";
            Multipart multipart = new MimeMultipart();
            messageBodyPart0.setContent(msg, "text/html");
            multipart.addBodyPart(messageBodyPart0);
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart1.setDataHandler(new DataHandler(source));
            messageBodyPart1.setFileName(fileName.replace(rootLocation.toString(), "").replace("/", ""));
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("send Mail to responsable " + receiver);
            

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }finally {
			File f = new File(fileName);
			f.delete();
		}
	}
	

	
	
	private void sendMailTeste(String nomPrenom,String receiver,List<String> cc,Date startDate,Date endDate,String fileName) {
		System.out.println("sendMail To " + nomPrenom);
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateFrom = startDate;
     
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "pretoria");
        props.put("mail.smtp.port", "25");
       
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usernamemail, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply-mtime@munisys.net.ma"));
            InternetAddress[] myActionsList = InternetAddress.parse(receiver);
            message.addRecipients(Message.RecipientType.TO,myActionsList);
            
            //InternetAddress[] myCcList = InternetAddress.parse("mehdibouhafs17@gmail.com");
            
           // message.addRecipients(Message.RecipientType.CC,myCcList);
            InternetAddress[] myCcList = new  InternetAddress[cc.size()];
            
            // To get the array of ccaddresses
               for( int i = 0; i < cc.size(); i++ ) {
               	myCcList[i] = new InternetAddress(cc.get(i));
                   message.addRecipient(Message.RecipientType.CC, myCcList[i]);
               }
          // InternetAddress[] myCCILIST = InternetAddress.parse("abassou@munisys.net.ma");
           // message.setRecipients(Message.RecipientType.BCC,myCCILIST);
            
            
            Calendar calendar =Calendar.getInstance();
            
            int week = calendar.get(Calendar.WEEK_OF_MONTH);
            
           // String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
            
            message.setSubject("Teste Rapport d'activité M-Time de "+nomPrenom+" de la semaine N°" +week );
            
            BodyPart messageBodyPart0 = new MimeBodyPart();
                        
            String msg = "<h4>Bonjour "+nomPrenom+",</h4>" + 
            		"<p>Je vous prie de trouver ci-joint le rapport d&acute;activité M-TIME de la semaine du "+formater.format(dateFrom)+" Au "+formater.format(endDate) +"</p>" +
            		"<p><strong>Cordialement</strong></p>"+
            		"<p><strong>M-TIME</strong></p>";
            Multipart multipart = new MimeMultipart();
            messageBodyPart0.setContent(msg, "text/html");
            multipart.addBodyPart(messageBodyPart0);
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart1.setDataHandler(new DataHandler(source));
            messageBodyPart1.setFileName(fileName.replace(rootLocation.toString(), "").replace("/", ""));
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("send mail to "+ nomPrenom);
            

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }finally {
			File f = new File(fileName);
			f.delete();
		}
	}
	
	
	public ServiceDao getService() {
		return service;
	}
	public void setService(ServiceDao service) {
		this.service = service;
	}
	
	public ServiceDao getUserService() {
		return userService;
	}
	public void setUserService(ServiceDao userService) {
		this.userService = userService;
	}
	public UserRepository getUserDao() {
		return userDao;
	}
	public void setUserDao(UserRepository userDao) {
		this.userDao = userDao;
	}
	public ActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	public static String getUsernamemail() {
		return usernamemail;
	}
	public static String getPassword() {
		return password;
	}
	
	private static String getProduits(Activity activity) {
		String prods="";
		Set<Produit> produits = null;
		if(activity instanceof ActivityProject) {
			 produits = (Set<Produit>) ((ActivityProject)activity).getProduits();
		}
		if(activity instanceof ActivityRequest) {
			produits = (Set<Produit>) ((ActivityRequest)activity).getProduits();
		}
		
		if(activity instanceof ActivityPM) {
			String produit = ((ActivityPM)activity).getProduit();
			produits = new HashSet<Produit>();
			Produit p = new Produit();
			p.setProduit(produit);
			if(produit!=null)
			produits.add(p);
			Set<Produit> produitsPM = (Set<Produit>) ((ActivityPM)activity).getProduits();
			if(produitsPM!=null  && !produitsPM.isEmpty())
			produits.addAll(produitsPM);
		}
		
		if(activity instanceof ActivityDevCompetence) {
			String produit = ((ActivityDevCompetence)activity).getProduit();
			produits = new HashSet<Produit>();
			Produit p = new Produit();
			p.setProduit(produit);
			if(produit!=null)
			produits.add(p);
			Set<Produit> produitsDevCompetence = (Set<Produit>) ((ActivityDevCompetence)activity).getProduits();
			if(produitsDevCompetence!=null && !produitsDevCompetence.isEmpty())
			produits.addAll(produitsDevCompetence);
		}
   	 
		if(produits!=null && !produits.isEmpty()) {
   		 List<String> produitsAll = new ArrayList<String>();
   	 
       	 for(Produit p : produits ) {
       		 if(p!=null && p.getProduit()!=null)
       		produitsAll.add(p.getProduit());
       	 }
       	  prods = String.join(",", produitsAll);
       	  
   	 }
		return prods;
	}
	
	@Override
	public void notifyHotlineByActivity(Activity activity, String mailHotline,Boolean newActivity) {
		
		String users = "";
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		
     
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "pretoria");
        props.put("mail.smtp.port", "25");
       
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usernamemail, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply-mtime@munisys.net.ma"));
            InternetAddress[] myActionsList = InternetAddress.parse(mailHotline);
            message.setRecipients(Message.RecipientType.TO,myActionsList);
            String msg="";
            int hours = (int) (activity.getDurtion() / 60);
	        int minutes = (int) (activity.getDurtion() % 60);
	        String duration =  String.format("%d", hours) + ":"+String.format("%02d", minutes);
            String produits = getProduits(activity);
            
            AppUser user = userDao.findByUsername(activity.getUser().getUsername());
            
	        if( activity instanceof ActivityRequest) {
            	
            	ActivityRequest activityRequest = (ActivityRequest)activity;
            	  
            	 message.setSubject(checkActionActivity(newActivity)+ "activtée support ("+ activityRequest.getRequest().getRqtExcde()+") réalisé par "+ user.getFirstName()+  " " +user.getLastName());
                 
                  msg = "<h4>Bonjour</h4>" + 
                 		"<p>"+checkActionActivityHTML(newActivity)+ "activitée support réalisé par  "+ user.getFirstName()+  " " +user.getLastName()+" et voici ci-dessous le détail.</p>" + 
                 		"<p><strong>Numéro de demande :</strong> "+ activityRequest.getRequest().getRqtExcde()+"</p>" + 
                 		"<p><strong>Date :</strong> "+ formater.format(activityRequest.getDteStrt())+"</p>" +
                 		"<p><strong>Durée :</strong> "+ duration+"</p>" + 
                 		"<p><strong>Client :</strong> "+ activityRequest.getCustomer().getName()+"</p>" + 
                 		"<p><strong>Produits :</strong> "+ produits+"</p>" + 
                 		"<p><strong>Role :</strong> "+ (activityRequest.isPrincipal()==true?"Principale":"Secondaire")+"</p>" +
                 		"<p><strong>Etat :</strong> "+ (activityRequest.isEtat()==false?"En cours de traitement":"Cloturer")+"</p>" +
                 		"<p><strong>Lieu :</strong> "+ activityRequest.getLieu()+"</p>" +
                 		"<p><strong>Ville :</strong> "+ activityRequest.getVille()+"</p>" +
                 		"<p><strong>Commentaire :</strong> "+ activity.getComments()+"</p>" + 
                 		"<p><strong>Cordialement</strong></p>"+
                 		"<p>MTime</p>";
            }else {
            	if(activity instanceof ActivityPM) {
            		
            		ActivityPM activityPM = (ActivityPM)activity;
            		message.setSubject(checkActionActivity(newActivity)+"activtée PM ("+ activityPM.getRequest().getRqtExcde()+") réalisé par "+ user.getFirstName()+  " " +user.getLastName());
                    
                    msg = "<h4>Bonjour</h4>" + 
                   		"<p>"+checkActionActivityHTML(newActivity)+ "activitée PM réalisé par  "+ user.getFirstName()+  " " +user.getLastName()+" et voici ci-dessous le détail.</p>" + 
                   		"<p><strong>Numéro de demande :</strong> "+ activityPM.getRequest().getRqtExcde()+"</p>" + 
                   		"<p><strong>Date :</strong> "+ formater.format(activityPM.getDteStrt())+"</p>" +
                   		"<p><strong>Durée :</strong> "+ duration+"</p>" + 
                   		"<p><strong>Client :</strong> "+ activityPM.getCustomer().getName()+"</p>" + 
                   		"<p><strong>Produits :</strong> "+ produits+"</p>" + 
                   		"<p><strong>Role :</strong> "+ (activityPM.isPrincipal()==true?"Principale":"Secondaire")+"</p>" +
                   		"<p><strong>Etat :</strong> "+ (activityPM.isEtat()==false?"En cours de traitement":"Cloturer")+"</p>" +
                   		"<p><strong>Lieu :</strong> "+ activityPM.getLieu()+"</p>" +
                   		"<p><strong>Ville :</strong> "+ activityPM.getVille()+"</p>" +
                   		"<p><strong>Commentaire :</strong> "+ activity.getComments()+"</p>" + 
                   		"<p><strong>Cordialement</strong></p>"+
                   		"<p>MTime</p>";
            	}
            }
           
           
	        BodyPart messageBodyPart0 = new MimeBodyPart();
            
           
            Multipart multipart = new MimeMultipart();
            messageBodyPart0.setContent(msg, "text/html");
            multipart.addBodyPart(messageBodyPart0);
         
            message.setContent(multipart);
            Transport.send(message);

            

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
	}
	
	private String checkActionActivity(Boolean newActivity) {
		if(newActivity==null) {
			return "Suppression de l'";
		}else {
			if(newActivity==true ) {
				return  "Nouvelle "; 
			}else{
				return "Mise à jour de l'";
			}
		}
	}
	private String checkActionActivityHTML(Boolean newActivity) {
		if(newActivity==null) {
			return "Suppression de l&acute";
		}else {
			if(newActivity==true ) {
				return  "Nouvelle "; 
			}else{
				return "Mise à jour de l&acute";
			}
		}
	}
	
	private Date addHoursToDate(Date date) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date);               // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 1);      // adds one hour
		return cal.getTime();
	}
	
	private Date lessHoursToDate(Date date ) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date);               // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, -1);      // adds one hour
		return cal.getTime();
	}

}
