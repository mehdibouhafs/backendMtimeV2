package ma.munisys.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.munisys.dao.ObjectifRepository;
import ma.munisys.dao.ObjectifUserRepository;
import ma.munisys.entities.Objectif;
import ma.munisys.entities.ObjectifUser;

@Service
public class ObjectifServiceImpl implements ObjectifService {
	
	@Autowired
	private ObjectifRepository objectifRepository;
	
	@Autowired
	private ObjectifUserRepository objectifUserRepository;

	@Override
	public Objectif saveObjectif(Objectif objectif) {
		for (ObjectifUser objectifUser : objectif.getUsers()) {
			objectifUser.setObjectif(objectif);
		}
		return objectifRepository.save(objectif);
	}

	@Override
	public Page<Objectif> getAllObjectifs(String mc, int page, int size) {
		return objectifRepository.getAllObjectifs("%"+mc+"%", new PageRequest(page-1, size));
	}

	@Override
	public List<Objectif> getMyObjectifsValide(String username) {
		return objectifRepository.getMyObjectifsValide(username, new Date());
	}

	@Transactional
	@Override
	public void updateTaux(Long id, double taux) {
		objectifUserRepository.updateTaux(id, taux);
	}

}
