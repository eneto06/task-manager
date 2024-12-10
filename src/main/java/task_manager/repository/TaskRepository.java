package task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task_manager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    

}