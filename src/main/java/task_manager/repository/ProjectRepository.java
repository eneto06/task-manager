package task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import task_manager.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    

}
