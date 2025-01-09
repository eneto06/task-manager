package task_manager.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
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

import task_manager.dto.TaskDto;
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

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.listTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> consultTask(@PathVariable Long id) {
        TaskDto task = taskService.findTasksById(id);

        if (Objects.isNull(task)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        TaskDto task = taskService.findTasksById(id);

        if (Objects.isNull(task)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task dataTask) {

        TaskDto task = taskService.findTasksById(id);

        if (Objects.isNull(task)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        task.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, dataTask));
    }
}
