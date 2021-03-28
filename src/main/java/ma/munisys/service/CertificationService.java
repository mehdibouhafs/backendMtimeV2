package ma.munisys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ma.munisys.entities.Certification;
import ma.munisys.entities.EmpCertification;
public interface CertificationService {
	
	public Certification saveCertification(Certification certification);
	
	
	public Certification findCertificationById(Long id);
	
	public Page<Certification> getByPage(String mc,int page,int size);
	
	public List<Certification> findAll();
	
	public Certification updateCertification(Long id, Certification certification);
	
	public void deleteCertification(Long id);
	
	public Page<Certification> getMyCertifications(String username, String mc, int page, int size);
	
	public Page<Certification> getCertificationToValide(Long idService, int page, int size);
	
	public List<Certification> getCertificationThisMonth(String username);
	
	public List<Certification> getCertificationNextMonth(String username);
	
	public String addCertificationToOutlook(Certification certification);
	
		
	
}
