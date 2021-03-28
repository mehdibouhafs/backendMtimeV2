package ma.munisys.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.munisys.dao.ActivityRepository;
import ma.munisys.dao.ProduitDAO;
import ma.munisys.dao.ProjectDao;
import ma.munisys.dao.ServiceDao;
import ma.munisys.entities.Activity;
import ma.munisys.entities.ActivityAvantVente;
import ma.munisys.entities.ActivityDevCompetence;
import ma.munisys.entities.ActivityHoliday;
import ma.munisys.entities.ActivityPM;
import ma.munisys.entities.ActivityProject;
import ma.munisys.entities.ActivityRequest;
import ma.munisys.entities.Produit;
import ma.munisys.entities.Project;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ProduitDAO produitDAO;
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private AccountService accountService;
	@Transactional
	@Override
	public Activity saveActivity(Activity activity) {

		/*if (activity.getClass().getSimpleName().equals("ActivityProject") && activity.isStatut() && ((ActivityProject) activity).getProject()!=null ) {

			Project p = ((ActivityProject) activity).getProject();
			projectDao.AddToDurtion(activity.getDurtion(), p.getPrjId());
		}*/
		activity.setStatut(true);
		
		
		
		
		Set<Produit> newProduits=new HashSet<Produit>();
		if(activity instanceof ActivityProject) {
			Set<Produit> produits =((ActivityProject) activity).getProduits();
			for(Produit p : produits) {
				if(p.getId()==null) {
					Produit newProduit = new Produit();
					ma.munisys.entities.Service c = accountService.getService(activity.getUser().getUsername());
					
					newProduit.setServName(c.getServName());
					newProduit.setGroupe("Autre");
					newProduit.setProduit(p.getProduit());
					newProduit = produitDAO.save(newProduit);
					newProduits.add(newProduit);
				}else {
					newProduits.add(p);
				}
			}
			((ActivityProject) activity).setProduits(newProduits);
		}
		
		if(activity instanceof ActivityPM) {
			Set<Produit> produits =((ActivityPM) activity).getProduits();
			for(Produit p : produits) {
				if(p.getId()==null) {
					Produit newProduit = new Produit();
					ma.munisys.entities.Service c = accountService.getService(activity.getUser().getUsername());
					newProduit.setServName(c.getServName());
					newProduit.setGroupe("Autre");
					newProduit.setProduit(p.getProduit());
					newProduit = produitDAO.save(newProduit);
					newProduits.add(newProduit);
				}else {
					newProduits.add(p);
				}
			}
			((ActivityPM) activity).setProduits(newProduits);
		}
		
		if(activity instanceof ActivityDevCompetence) {
			Set<Produit> produits =((ActivityDevCompetence) activity).getProduits();
			for(Produit p : produits) {
				if(p.getId()==null) {
					Produit newProduit = new Produit();
					ma.munisys.entities.Service c = accountService.getService(activity.getUser().getUsername());
					newProduit.setServName(c.getServName());
					newProduit.setGroupe("Autre");
					newProduit.setProduit(p.getProduit());
					newProduit = produitDAO.save(newProduit);
					newProduits.add(newProduit);
				}else {
					newProduits.add(p);
				}
			}
			((ActivityDevCompetence) activity).setProduits(newProduits);
		}
		
		
		if(activity instanceof ActivityHoliday && activity.getDurtion()==0 && activity.getDteStrt().equals(activity.getDteEnd())) {
			activity.setDurtion(1440.00);
		}
		
		Activity a = activityRepository.save(activity);
		
		if(activity instanceof ActivityRequest || activity instanceof ActivityPM) {
			emailService.notifyHotlineByActivity(activity, "hotline.value@munisys.net.ma",true);
		}
		
		return a;
	}

	@Override
	public Activity findActivityById(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public Page<Activity> findMyActivitiesByMc(String username, String mc, int page, int size,
			List<String> typeSelected) {
		if (typeSelected.size() == 0)
			return activityRepository.findMyActivitiesByMc(username, "%" + mc + "%", new PageRequest(page - 1, size));
		else {

			List<String> typeOfActivity = new ArrayList<String>();
			for (String type : typeSelected) {
				switch (type) {
				case "AP":
					typeOfActivity.add("Activité projet");
					break;
				case "AS":
					typeOfActivity.add("Activité support");
					break;
				case "ASSI":
					typeOfActivity.add("Activité SI");
					break;
				case "ACM":
					typeOfActivity.add("Activité commerciale");
					break;
				case "AC":
					typeOfActivity.add("Activité congé");
					break;
				case "AR":
					typeOfActivity.add("Activité recouvrement");
					break;
				}
			}
			System.out.println(typeOfActivity.toString());
			String[] table = new String[typeOfActivity.size()];
			table = typeOfActivity.toArray(table);
			return activityRepository.findMyActivitiesByMcAndType(username, "%" + mc + "%",
					new PageRequest(page - 1, size), table);
		}
	}

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

	@Transactional
	@Override
	public Activity updateActivity(Long id, Activity activity) {
		activity.setId(id);
		activity.setStatut(true);
		/*if (activity.getClass().getSimpleName().equals("ActivityProject") && activity.isStatut()) {
			double lastDurtion = activityRepository.findOne(id).getDurtion();
			Project p = ((ActivityProject) activity).getProject();
			projectDao.substractFromDurtion(lastDurtion, p.getPrjId());
			projectDao.AddToDurtion(activity.getDurtion(), p.getPrjId());
		}
		*/
		Set<Produit> newProduits=new HashSet<Produit>();
		if(activity instanceof ActivityProject) {
			Set<Produit> produits =((ActivityProject) activity).getProduits();
			for(Produit p : produits) {
				if(p.getId()==null) {
					Produit newProduit = new Produit();
					ma.munisys.entities.Service c = accountService.getService(activity.getUser().getUsername());
					newProduit.setServName(c.getServName());
					newProduit.setGroupe("Autre");
					newProduit.setProduit(p.getProduit());
					newProduit = produitDAO.save(newProduit);
					newProduits.add(newProduit);
				}else {
					newProduits.add(p);
				}
			}
			((ActivityProject) activity).setProduits(newProduits);
		}
		
		if(activity instanceof ActivityPM) {
			Set<Produit> produits =((ActivityPM) activity).getProduits();
			for(Produit p : produits) {
				if(p.getId()==null) {
					Produit newProduit = new Produit();
					ma.munisys.entities.Service c = accountService.getService(activity.getUser().getUsername());
					newProduit.setServName(c.getServName());
					newProduit.setGroupe("Autre");
					newProduit.setProduit(p.getProduit());
					newProduit = produitDAO.save(newProduit);
					newProduits.add(newProduit);
				}else {
					newProduits.add(p);
				}
			}
			((ActivityPM) activity).setProduits(newProduits);
		}
		
		if(activity instanceof ActivityDevCompetence) {
			Set<Produit> produits =((ActivityDevCompetence) activity).getProduits();
			for(Produit p : produits) {
				if(p.getId()==null) {
					Produit newProduit = new Produit();
					ma.munisys.entities.Service c = accountService.getService(activity.getUser().getUsername());
					newProduit.setServName(c.getServName());
					newProduit.setGroupe("Autre");
					newProduit.setProduit(p.getProduit());
					newProduit = produitDAO.save(newProduit);
					newProduits.add(newProduit);
				}else {
					newProduits.add(p);
				}
			}
			((ActivityDevCompetence) activity).setProduits(newProduits);
		}
		
		if(activity instanceof ActivityHoliday && activity.getDurtion()==0 && activity.getDteStrt().equals(activity.getDteEnd())) {
			activity.setDurtion(1440.00);
		}
		
		Activity a = activityRepository.save(activity);
		
		if(activity instanceof ActivityRequest || activity instanceof ActivityPM) {
			emailService.notifyHotlineByActivity(activity, "hotline.value@munisys.net.ma",false);
			
		}
		
		return a;
	}

	@Override
	public void deleteActivity(Long id) {

		Activity activity = activityRepository.findActivity(id); 
		
		activityRepository.delete(id);
		
		if(activity instanceof ActivityRequest || activity instanceof ActivityPM) {
			emailService.notifyHotlineByActivity(activity, "hotline.value@munisys.net.ma",null);
		}
		
	}

	@Override
	public List<Activity> findActivityBetween(String username, Date dteStrt, Date dteEnd) {
		// TODO Auto-generated method stub
		return activityRepository.findActivityBetween(username, dteStrt, dteEnd);
	}

	@Override
	public List<Activity> findActivityBetween(Long id, String username, Date dteStrt, Date dteEnd) {
		// TODO Auto-generated method stub
		return activityRepository.findActivityBetween(id, username, dteStrt, dteEnd);
	}

	@Override
	public Page<Activity> getUserActivities(String username, int page, int size) {
		// TODO Auto-generated method stub
		return activityRepository.getUserActivities(username, new PageRequest(page - 1, size));
	}

	@Override
	public Page<Activity> findAllActivitiesByMc(String mc, int page, int size, List<String> typeSelected) {
		// TODO Auto-generated method stub
		if (typeSelected.size() == 0) {
			return activityRepository.findAllActivitiesByMc("%" + mc + "%", new PageRequest(page - 1, size));
		}

		else {
			List<String> typeOfActivity = new ArrayList<String>();
			for (String type : typeSelected) {
				switch (type) {
				case "AP":
					typeOfActivity.add("Activité projet");
					break;
				case "AS":
					typeOfActivity.add("Activité support");
					break;
				case "ASSI":
					typeOfActivity.add("Activité SI");
					break;
				case "ACM":
					typeOfActivity.add("Activité commerciale");
					break;
				case "AC":
					typeOfActivity.add("Activité congé");
					break;
				case "AR":
					typeOfActivity.add("Activité recouvrement");
					break;
				}
			}
			System.out.println(typeOfActivity.toString());
			String[] table = new String[typeOfActivity.size()];
			table = typeOfActivity.toArray(table);
			return activityRepository.findAllActivitiesByMcAndType("%" + mc + "%", new PageRequest(page - 1, size),
					table);
		}
	}

	@Override
	public Activity getLastActivity(String username) {
		// TODO Auto-generated method stub
		
		 if(activityRepository.getLastActivity(username)!=null &&  !activityRepository.getLastActivity(username).isEmpty()) {
			 return activityRepository.getLastActivity(username).get(0);
		 }
		return null;
	}

	@Override
	public List<Activity> getActivityToday(String username) {

		return activityRepository.getActivityToday(username, new Date());
	}

	@Override
	public List<Activity> getActivityTomorrow(String username) {

		return activityRepository.getActivityTomorrow(username, new Date());
	}

	@Override
	public List<Activity> findAllMyActivitiesByDates(String username, Date dateDebut, Date dateFin) {
		return activityRepository.findAllMyActivitiesByDates(username, dateDebut, dateFin);
	}

	@Override
	public List<Activity> findAllMyActivitiesByDatesForDay(String username, Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return activityRepository.findAllMyActivitiesByDatesForDay(username, dateDebut, dateFin);
	}

	@Override
	public List<Activity> findAllActivitiesByDates(Date dateDebut, Date dateFin) {
		return activityRepository.findAllActivitiesByDates(dateDebut, dateFin);
	}

	@Override
	public List<Activity> findAllActivitiesByDatesForDay(Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return activityRepository.findAllActivitiesByDatesForDay(dateDebut, dateFin);
	}

	@Override
	public Page<Activity> getActivityToDo(String username, String mc, int page, int size) {
		// TODO Auto-generated method stub
		return activityRepository.getActivityToDo(username, "%" + mc + "%", new Date(),
				new PageRequest(page - 1, size));
	}

	@Override
	public Page<Activity> getMyActivityHoliday(String username, int page, int size) {
		// TODO Auto-generated method stub
		return activityRepository.getMyActivityHoliday(username, new PageRequest(page - 1, size));
	}

	@Override
	public Page<Activity> getActivityRequestByTicket(String rqtExcde, int page, int size) {
		return activityRepository.getActivityRequestByTicket(rqtExcde, new PageRequest(page - 1, size));
	}

	@Override
	public Page<Activity> getActivityByService(Long idService, String username, String mc, int page, int size,
			List<String> typeSelected) {

		if (typeSelected.size() == 0) {
			return activityRepository.getActivityByService(idService, "%" + username + "%", "%" + mc + "%",
					new PageRequest(page - 1, size));
		}

		else {
			List<String> typeOfActivity = new ArrayList<String>();
			for (String type : typeSelected) {
				switch (type) {
				case "AP":
					typeOfActivity.add("Activité projet");
					break;
				case "AS":
					typeOfActivity.add("Activité support");
					break;
				case "ASSI":
					typeOfActivity.add("Activité SI");
					break;
				case "ACM":
					typeOfActivity.add("Activité commerciale");
					break;
				case "AC":
					typeOfActivity.add("Activité congé");
					break;
				case "AR":
					typeOfActivity.add("Activité recouvrement");
					break;
				}
			}
			System.out.println(typeOfActivity.toString());
			String[] table = new String[typeOfActivity.size()];
			table = typeOfActivity.toArray(table);
			return activityRepository.getActivityByServiceAndType(idService, "%" + username + "%", "%" + mc + "%",
					new PageRequest(page - 1, size), table);
		}
	}

	@Override
	public List<Activity> findAllActivitiesByDatesAndService(Long idService, String username, Date dateDebut,
			Date dateFin) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dateDebut);
	    calendar.add(Calendar.DATE, -1);
	    dateDebut = calendar.getTime();
	    
	     
	    calendar.setTime(dateFin);
	    calendar.add(Calendar.DATE, 1);
	    dateFin = calendar.getTime();
		return activityRepository.findAllActivitiesByDatesAndService(idService, "%" + username + "%", dateDebut,
				dateFin);
	}

	@Override
	public List<Activity> findAllActivitiesByDatesForDayAndService(Long idService, String username, Date dateDebut,
			Date dateFin) {
		return activityRepository.findAllActivitiesByDatesForDayAndService(idService, "%" + username + "%", dateDebut,
				dateFin);
	}

	@Override
	public Page<Activity> findMyActivityProject(String username, Date start, String mc, int page, int size) {
		if (start == null)
			return activityRepository.findMyActivityProject(username, "%" + mc + "%", new PageRequest(page - 1, size));
		else
			return activityRepository.findMyActivityProjectByDate(username, start, "%" + mc + "%",
					new PageRequest(page - 1, size));
	}

	@Override
	public Page<Activity> getActivityPlanifiedDirection(String username, String mc, int page, int size) {

		return activityRepository.getActivityPlanifiedDirection("%"+username+"%", "%" + mc + "%",
				new PageRequest(page - 1, size));

	}

	@Override
	public List<Long> findActivityDateSaisieBetween(String username, Date dteStrt, Date dteEnd) {
		return activityRepository.findActivityBetweenDateSaisie(username, dteStrt, dteEnd);
	}

}
