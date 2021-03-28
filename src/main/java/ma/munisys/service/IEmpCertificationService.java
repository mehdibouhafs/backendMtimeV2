package ma.munisys.service;

import java.util.List;

import ma.munisys.entities.EmpCertification;

public interface IEmpCertificationService {


	public List<EmpCertification> findAll();
	public EmpCertification validCertification(EmpCertification empCertification);
	
}
