package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.munisys.entities.AppProfile;

public interface ProfileRepository extends JpaRepository<AppProfile, Long> {
	
	public AppProfile findByPrflName(String profileName);
}
