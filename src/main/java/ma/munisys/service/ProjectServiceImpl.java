package ma.munisys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.munisys.entities.Project;

@Service
@Transactional // spring pas javax
public class ProjectServiceImpl implements ProjectService {
	
	
	
	@Autowired
	private ma.munisys.dao.ProjectDao ProjectDao;
	


	@Override
	public List<Project> findProjectByCustomer(String codeCustomer) {
		return ProjectDao.findProjectByCustomer(codeCustomer);
	}


}
