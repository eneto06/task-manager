package task_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import task_manager.model.Project;
import task_manager.repository.ProjectRepository;

public class ProjectService {

    public Project registerProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> listRegisteredProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> findProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Autowired
    private ProjectRepository projectRepository;

}
