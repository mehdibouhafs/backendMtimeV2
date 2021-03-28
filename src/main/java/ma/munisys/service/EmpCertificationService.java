package ma.munisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.munisys.dao.EmpCertificationDAO;
import ma.munisys.entities.EmpCertification;

@Service
public class EmpCertificationService implements IEmpCertificationService {

	@Autowired
	EmpCertificationDAO empCertificationDAO;
	
	@Override
	public EmpCertification validCertification(EmpCertification empCertification) {
		
		return empCertificationDAO.save(empCertification);
	}

	@Override
	public List<EmpCertification> findAll() {
		return empCertificationDAO.findAll();
	}

}
