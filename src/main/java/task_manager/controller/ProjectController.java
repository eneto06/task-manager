package task_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import task_manager.model.Project;
import task_manager.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    private ProjectService projectService;

    public ResponseEntity<Project> registerNewProject(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.registerProject(project));
    }

    @GetMapping("/listRegisteredProjects")
    public ResponseEntity<List<Project>> listRegisteredProjects() {
        return ResponseEntity.ok().body(projectService.listRegisteredProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findProjects(@PathVariable Long id) {

        Optional<Project> project = projectService.findProjectById(id); 
        
        if (project.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(project.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        Optional<Project> project = projectService.findProjectById(id);

        if (project.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {

        Optional<Project> projectOpt = projectService.findProjectById(id);

        if (projectOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        project.setId(id);

        return ResponseEntity.ok().body(projectService.registerProject(project));
    }




}
