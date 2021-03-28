package ma.munisys.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ma.munisys.dao.FormationRepository;
import ma.munisys.entities.DateFormation;
import ma.munisys.entities.EmpCertification;
import ma.munisys.entities.EmpFormation;
import ma.munisys.entities.Formation;

@Service
public class FormationServiceImpl implements FormationService  {
	
	@Autowired
	private FormationRepository formationRepository;
	
	@Autowired
	private OutlookService outlookService;

	@Override
	public Formation saveFormation(Formation formation) {
		
		if(formation.getId() !=null) {
			Formation lastformation =formationRepository.findOne(formation.getId());
			outlookService.deleteAppoitementFormation(lastformation);
		}
		
		for (EmpFormation empFormation : formation.getCandidats()) {
			empFormation.setFormation(formation);
		}
		for (DateFormation dateFormation : formation.getDates()) {
			dateFormation.setFormation(formation);
			//String idOutlook = outlookService.addAppointement(formation, dateFormation);
			//dateFormation.setIdOutlook(idOutlook);
		}
		
		return formationRepository.save(formation);
	}
	
	@Override
	public String addFormationToOutlook(Formation formation) {
		
		for (EmpFormation empFormation : formation.getCandidats()) {
			empFormation.setFormation(formation);
		}
		
		for (DateFormation dateFormation : formation.getDates()) {
			dateFormation.setFormation(formation);
			String idOutlook = outlookService.addAppointementFormation(formation, dateFormation);
			dateFormation.setIdOutlook(idOutlook);
		}
		
		 formationRepository.save(formation);
		
		return "success";

	}
	
	@Override
	public Formation updateFormation(Long id, Formation updatedformation) {
		
		/*
		
		for (EmpFormation empFormation : updatedformation.getCandidats()) {
			empFormation.setFormation(updatedformation);
		}
		for (DateFormation dateFormation : updatedformation.getDates()) {
			dateFormation.setFormation(updatedformation);
			//String idOutlook = outlookService.addAppointement(formation, dateFormation);
			//dateFormation.setIdOutlook(idOutlook);
		}
		*/
		return formationRepository.save(updatedformation);
	}


	
	

	@Override
	public Formation findFormationById(Long id) {
		// TODO Auto-generated method stub
		return formationRepository.findOne(id);
	}

	@Override
	public Page<Formation> getByPage(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return formationRepository.chercher("%"+mc+"%", new PageRequest(page-1, size));
	}

	@Override
	public List<Formation> findAll() {
		// TODO Auto-generated method stub
		return formationRepository.findAll();
	}

	

	@Override
	public void deleteFormation(Long id) {
		outlookService.deleteAppoitementFormation(formationRepository.findOne(id));
		formationRepository.delete(id);
		
		
		
	}

	@Override
	public Page<Formation> getMyFormations(String username, String mc, int page, int size) {
		return formationRepository.getMyFormations(username, "%"+mc+"%", new PageRequest(page-1, size));
	}

	@Override
	public Page<Formation> getFormationToValide(Long idService, int page, int size) {
		return formationRepository.getFormationToValide(idService, new PageRequest(page-1, size));
	}

	@Override
	public Page<Formation> getFormationGroupe(Long idService, int page, int size) {
		
		return formationRepository.getFormationGroupe(idService, new PageRequest(page-1, size));
	}




}
