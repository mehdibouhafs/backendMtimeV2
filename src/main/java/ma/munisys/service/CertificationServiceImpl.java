package ma.munisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ma.munisys.dao.CertificationRepository;
import ma.munisys.entities.Certification;

@Service
public class CertificationServiceImpl implements CertificationService  {
	
	@Autowired
	private CertificationRepository certificationRepository;

	@Override
	public Certification saveCertification(Certification certification) {
		// TODO Auto-generated method stub
		return certificationRepository.save(certification);
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
		certificationRepository.delete(id);
		
	}

}
