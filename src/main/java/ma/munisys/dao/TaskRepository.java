package ma.munisys.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ma.munisys.entities.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
