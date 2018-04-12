package ma.munisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ma.munisys.dao.FormationRepository;
import ma.munisys.entities.Formation;

@Service
public class FormationServiceImpl implements FormationService  {
	
	@Autowired
	private FormationRepository formationRepository;

	@Override
	public Formation saveFormation(Formation formation) {
		// TODO Auto-generated method stub
		return formationRepository.save(formation);
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
	public Formation updateFormation(Long id, Formation updatedformation) {
		
		updatedformation.setId(id);
		return formationRepository.save(updatedformation);
	}

	@Override
	public void deleteFormation(Long id) {
		formationRepository.delete(id);
		
	}

}
