package ma.munisys.service;


import java.util.List;

import org.springframework.data.domain.Page;

import ma.munisys.entities.Objectif;

public interface ObjectifService {
	
	public Objectif saveObjectif(Objectif objectif);
	
	public Page<Objectif> getAllObjectifs(String mc, int page, int size);
	
	public List<Objectif> getMyObjectifsValide(String username);
	
	public void updateTaux(Long id, double taux);
	
}
