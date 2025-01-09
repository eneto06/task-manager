package task_manager.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import task_manager.dto.ProjectDto;
import task_manager.model.Project;
import task_manager.repository.ProjectRepository;

public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> listProjects() {
        return projectRepository.findAll();
    }

    public ProjectDto findProjectById(Long id) {
        Optional<Project> projectOpt =  projectRepository.findById(id);

        if (projectOpt.isPresent()) {
            return projectOpt.get().toDto();
        }

        return null;
    }

    public List<ProjectDto> findProjectByResponsibleId(Long id) {
        List<Project> projects = projectRepository.findByResponsible_Id(id);
        return projects.stream().map(Project::toDto).toList();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project updaProject(Long id, Project dataProject) {
        Optional<Project> projectOpt = projectRepository.findById(id);

        if (projectOpt.isPresent()) {
            Project project = projectOpt.get();

            project.setName(dataProject.getName());
            project.setDescription(dataProject.getDescription());
            project.setResponsible(dataProject.getResponsible());
            project.setTasks(dataProject.getTasks());

            return projectRepository.save(project);
        }

        return null;
    }

}
