package ma.munisys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ma.munisys.entities.Certification;
public interface CertificationService {
	
	public Certification saveCertification(Certification certification);
	
	
	public Certification findCertificationById(Long id);
	
	public Page<Certification> getByPage(String mc,int page,int size);
	
	public List<Certification> findAll();
	
	public Certification updateCertification(@PathVariable Long id, Certification certification);
	
	public void deleteCertification(Long id);
		
	
}
