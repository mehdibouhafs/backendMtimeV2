package ma.munisys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ma.munisys.entities.Certification;
import ma.munisys.entities.Contact;
import ma.munisys.entities.Formation;

public interface FormationService {
	
	public Formation saveFormation(Formation formation);
	
	
	public Formation findFormationById(Long id);
	
	public Page<Formation> getByPage(String mc,int page,int size);
	
	public List<Formation> findAll();
	
	public Formation updateFormation(Long id, Formation formation);
	
	public void deleteFormation(Long id);
	
	public Page<Formation> getMyFormations(String username, String mc, int page, int size);
	
	public Page<Formation> getFormationToValide(Long idService, int page, int size);
	
	public Page<Formation> getFormationGroupe(Long idService, int page, int size);
	
	public String addFormationToOutlook(Formation formation);
	
	
	
}
