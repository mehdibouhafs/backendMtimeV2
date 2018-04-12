package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.munisys.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	
	public AppUser findByUsername(String username);

}
