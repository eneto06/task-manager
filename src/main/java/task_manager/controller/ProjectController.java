package task_manager.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import task_manager.dto.ProjectDto;
import task_manager.model.Project;
import task_manager.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<Project> saveProject(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.saveProject(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> listProjects() {
        return ResponseEntity.ok().body(projectService.listProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findProjectsById(@PathVariable Long id) {

        ProjectDto project = projectService.findProjectById(id); 
        
        if (Objects.isNull(project)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(project);
    }

    @GetMapping("/responsible{id}")
    public ResponseEntity<List<ProjectDto>> findProjectByResponsibleId(@PathVariable Long id) {
        return ResponseEntity.ok().body(projectService.findProjectByResponsibleId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        ProjectDto project = projectService.findProjectById(id);

        if (Objects.isNull(project)) {
            return ResponseEntity.notFound().build();
        }

        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project dataProject) {

        ProjectDto projectOpt = projectService.findProjectById(id);

        if (Objects.isNull(projectOpt)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(projectService.updaProject(id, dataProject));
    }




}
