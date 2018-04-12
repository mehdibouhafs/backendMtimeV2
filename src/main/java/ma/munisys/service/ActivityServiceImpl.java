package ma.munisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ma.munisys.dao.ActivityRepository;
import ma.munisys.entities.Activity;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity saveActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity findActivityById(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public Page<Activity> getByPage(int page, int size) {
		return activityRepository.chercher(new PageRequest(page-1, size));
	}

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

	@Override
	public Activity updateActivity(Long id, Activity activity) {
		activity.setId(id);
		return activityRepository.save(activity);
	}

	@Override
	public void deleteActivity(Long id) {
	
		activityRepository.delete(id);
	}

}
