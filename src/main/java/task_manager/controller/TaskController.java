package task_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import task_manager.model.Task;
import task_manager.service.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> registerNewTask(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.registerTask(task)); 
    }

    @GetMapping({"/list"})
    public ResponseEntity<List<Task>> listRegisteredTasks() {
        return ResponseEntity.ok().body(taskService.listTasks());
    }

    @GetMapping({"/listPageable"})
    public ResponseEntity<Page<Task>> listRegisteredTasksByPagination(Pageable pageable) {
        return ResponseEntity.ok().body(taskService.listTasks(pageable));
    }

    @GetMapping({"/{userId}/ tasks"})
    public ResponseEntity<List<Task>> listTaskByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.findTaskByUserId(userId);
        return tasks != null ? ResponseEntity.ok(tasks) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findTasksById(id);

        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(task.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findTasksById(id);

        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {

        Optional<Task> taskOpt = taskService.findTasksById(id);

        if (taskOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        task.setId(id);

        return ResponseEntity.ok().body(taskService.registerTask(task));
    }
}
