package ma.munisys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.munisys.dao.EmpFormationDAO;
import ma.munisys.entities.EmpFormation;

@Service
public class EmpFormationService implements IEmpFormationService {

	@Autowired
	EmpFormationDAO empFormationDAO;
	
	@Override
	public EmpFormation validFormation(EmpFormation empFormation) {
		
		return empFormationDAO.save(empFormation);
	}

}
