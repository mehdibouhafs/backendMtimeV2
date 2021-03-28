package ma.munisys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ma.munisys.dao.CertificationRepository;
import ma.munisys.entities.Certification;
import ma.munisys.entities.DateFormation;
import ma.munisys.entities.EmpCertification;
import ma.munisys.entities.EmpFormation;
import ma.munisys.entities.Formation;

@Service
public class CertificationServiceImpl implements CertificationService  {
	
	@Autowired
	private CertificationRepository certificationRepository;
	
	@Autowired
	private OutlookService outlookService;

	@Override
	public Certification saveCertification(Certification certification) {
		
		if(certification.getId() !=null) {
			Certification lastcertification =certificationRepository.findOne(certification.getId());
			outlookService.deleteAppoitementCertification(lastcertification);
		}
		
		for (EmpCertification empCertification : certification.getCandidats()) {
			empCertification.setCertification(certification);
		}
		
		return certificationRepository.save(certification);
	}
	
	
	@Override
	public String addCertificationToOutlook(Certification certification) {
		
		String idOutlook = outlookService.addAppointementCertification(certification);
		
		certification.setIdOutlook(idOutlook);
		
		certificationRepository.save(certification);
		
		return "success";

	}

	@Override
	public Certification findCertificationById(Long id) {
		// TODO Auto-generated method stub
		return certificationRepository.findOne(id);
	}

	@Override
	public Page<Certification> getByPage(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return certificationRepository.chercher("%"+mc+"%", new PageRequest(page-1, size));
	}

	@Override
	public List<Certification> findAll() {
		// TODO Auto-generated method stub
		return certificationRepository.findAll();
	}

	@Override
	public Certification updateCertification(Long id, Certification updatedCertification) {
		
		updatedCertification.setId(id);
		return certificationRepository.save(updatedCertification);
	}

	@Override
	public void deleteCertification(Long id) {
		outlookService.deleteAppoitementCertification(certificationRepository.findOne(id));
		certificationRepository.delete(id);
		
	}

	@Override
	public Page<Certification> getMyCertifications(String username, String mc, int page, int size) {
		
		return certificationRepository.getMyCertifications(username, "%"+mc+"%", new PageRequest(page-1, size));
	}

	@Override
	public Page<Certification> getCertificationToValide(Long idService, int page, int size) {
		return certificationRepository.getCertificationToValide(idService, new PageRequest(page-1, size));
	}

	@Override
	public List<Certification> getCertificationThisMonth(String username) {
		return certificationRepository.getCertificationOnThisMonth(username, new Date());
	}

	@Override
	public List<Certification> getCertificationNextMonth(String username) {
		return certificationRepository.getCertificationOnNextMonth(username, new Date());
	}

}
