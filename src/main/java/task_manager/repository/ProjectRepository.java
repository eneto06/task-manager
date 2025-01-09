package task_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import task_manager.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    List<Project> findByResponsible_Id(Long idUser);
}
